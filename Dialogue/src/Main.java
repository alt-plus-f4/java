import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Player player = new Player(100, 20, 15, 50);
            DialogueTree dialogueTree = loadDialogueFromFile("dialog.json", player);
            playGame(player, dialogueTree);
        } catch (IOException e) {
            System.out.println("Error: Dialogue file not found or invalid format.");
        }
    }

    public static DialogueTree loadDialogueFromFile(String fileName, Player player) throws IOException {
        try (InputStream is = new FileInputStream(fileName)) {
            JsonArray jsonDialogue = Json.createReader(is).readArray();
            List<DialogueStep> dialogueSteps = new ArrayList<>();

            for (JsonValue jsonStep : jsonDialogue) {
                JsonObject stepObject = (JsonObject) jsonStep;

                String id = stepObject.getString("id");
                String npcReply = stepObject.getString("npcReply");
                JsonArray jsonPlayerOptions = stepObject.getJsonArray("playerOptions");

                DialogueStep dialogueStep = new DialogueStep(id, npcReply, null);

                for (JsonValue jsonOption : jsonPlayerOptions) {
                    JsonObject optionObject = (JsonObject) jsonOption;

                    String playerReply = optionObject.getString("playerReply");
                    String nextStepId = optionObject.getString("nextStepId");
                    JsonArray jsonModifiers = optionObject.getJsonArray("modifiers");

                    DialogueOption dialogueOption = new DialogueOption(playerReply, null);

                    for (JsonValue jsonModifier : jsonModifiers) {
                        JsonObject modifierObject = (JsonObject) jsonModifier;
                        String modifierType = modifierObject.getString("type");
                        String modifierValue = modifierObject.getString("value");

                        switch (modifierType) {
                            case "Optional":
                                dialogueOption.addOptionalModifier(player -> player.CHA > Integer.parseInt(modifierValue));
                                break;
                            case "Requirement":
                                dialogueOption.addRequirementModifier(player -> {
                                    if (player.GOLD < Integer.parseInt(modifierValue)) {
                                        throw new Exception("Not enough gold!");
                                    }
                                });
                                break;
                            case "Reward":
                                dialogueOption.addRewardModifier(player -> {
                                    player.GOLD += Integer.parseInt(modifierValue);
                                    System.out.println("Received reward: " + modifierValue + " gold");
                                });
                                break;
                        }
                    }

                    dialogueStep.addPlayerOption(dialogueOption);
                    dialogueOption.setNextStepId(nextStepId);
                }

                dialogueSteps.add(dialogueStep);
            }

            if (dialogueSteps.isEmpty()) {
                throw new IOException("Invalid dialogue file format.");
            }

            return new DialogueTree(dialogueSteps.get(0));
        }
    }


    public static void playGame(Player player, DialogueTree dialogueTree) {
        DialogueStep currentStep = dialogueTree.root;

        Scanner scanner = new Scanner(System.in);

        while (currentStep != null) {
            System.out.println("Step ID: " + currentStep.id);
            System.out.println("NPC: " + currentStep.npcReply);

            int optionNumber = 1;
            for (DialogueOption option : currentStep.playerOptions) {
                if (option.optionalModifiers.stream().allMatch(modifier -> modifier.test(player))) {
                    System.out.println(optionNumber + ". " + option.playerReply);
                    optionNumber++;
                }
            }

            if (optionNumber == 1) {
                System.out.println("No player options available. Moving to the next step.");
                currentStep = currentStep.nextStep;
                continue;
            }

            System.out.print("Choose an option: ");
            int chosenOption = scanner.nextInt();

            DialogueOption selectedOption = currentStep.playerOptions.get(chosenOption - 1);

            try {
                for (IRequirement modifier : selectedOption.requirementModifiers) {
                    modifier.take(player);
                }

                for (IReward modifier : selectedOption.rewardModifiers) {
                    modifier.reward(player);
                }

                currentStep = selectedOption.nextStep;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Dialogue completed!");
    }
}
