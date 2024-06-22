import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Player player = new Player(100, 20, 15, 50);
            DialogueTree dialogueTree = loadDialogueFromFile("dialog.txt");
            printDialogueTree(dialogueTree.root, new HashSet<>());

            System.out.println(dialogueTree);
            playGame(player, dialogueTree);
        } catch (IOException e) {
            System.out.println("Error: Dialogue file not found.");
        }
    }

    public static void printDialogueTree(DialogueStep step, Set<String> visited) {
        if (step == null || visited.contains(step.getId())) {
            return;
        }

        System.out.println(step);
        for (DialogueOption option : step.getPlayerOptions()) {
            System.out.println(option);
        }

        visited.add(step.getId());

        for (DialogueOption option : step.getPlayerOptions()) {
            printDialogueTree(option.getNextStep(), visited);
        }
    }

    public static DialogueTree loadDialogueFromFile(String filename) throws IOException {
        Map<String, DialogueStep> steps = new HashMap<>();
        Map<String, DialogueOption> options = new HashMap<>();
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        for (String line : lines) {
            String[] parts = line.split(":");
            if (!parts[0].startsWith("option")) {
                DialogueStep step = new DialogueStep(parts[0], parts[1], null);
                steps.put(parts[0], step);
            }
        }

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts[0].startsWith("option")) {
                DialogueStep nextStep = steps.get(parts[2]);
                List<IRequirement> requirements = new ArrayList<>();
                List<IReward> rewards = new ArrayList<>();
                List<IOptional> optional = new ArrayList<>();

                if (parts.length > 3 && !parts[3].isEmpty()) {
                    String[] requirementStrings = parts[3].split(",");
                    for (String requirementString : requirementStrings)
                        requirements.add(parseRequirement(requirementString));
                }

                if (parts.length > 4 && !parts[4].isEmpty()) {
                    String[] rewardStrings = parts[4].split(",");
                    for (String rewardString : rewardStrings)
                        rewards.add(parseReward(rewardString));
                }

                if (parts.length > 5 && !parts[5].isEmpty())
                    optional.add(parseOptional(parts[5]));

                DialogueOption option = new DialogueOption(parts[1], nextStep);

                option.setOptionalModifiers(optional);
                option.setRewardModifiers(rewards);
                option.setRequirementModifiers(requirements);

                options.put(parts[0], option);
            }
        }

        for (String line : lines) {
            String[] parts = line.split(":");
            if (!parts[0].startsWith("option") && parts.length > 2) {
                String[] optionIDs = parts[2].split(",");
                DialogueStep step = steps.get(parts[0]);
                for (String optionID : optionIDs) {
                    DialogueOption option = options.get(optionID);
                    if (option != null) {
                        step.addPlayerOption(option);
                    }
                }
            }
        }

        return new DialogueTree(steps.get("entrance"));
    }

    public static IOptional parseOptional(String optionalString) {
        String[] parts = optionalString.split(" ");
        String attribute = parts[0];
        String operator = parts[1];
        int value = Integer.parseInt(parts[2]);

        return player -> {
            int playerValue = player.getAttribute(attribute);

            switch (operator) {
                case ">":
                    return playerValue > value;
                case "<":
                    return playerValue < value;
                case "==":
                    return playerValue == value;
                default:
                    throw new IllegalArgumentException("Unknown operator: " + operator);
            }
        };
    }


    private static IRequirement parseRequirement(String requirement) {
        if (requirement.contains("<")) {
            String[] parts = requirement.split("<");
            return player -> {
                int playerValue = player.getAttribute(parts[0]);
                if (playerValue < Integer.parseInt(parts[1])) {
                    throw new Exception("Not enough " + parts[0] + "!");
                }
            };
        } else if (requirement.contains(">=")) {
            String[] parts = requirement.split(">=");
            return player -> {
                int playerValue = player.getAttribute(parts[0]);
                if (playerValue < Integer.parseInt(parts[1])) {
                    throw new Exception("Not enough " + parts[0] + "!");
                }
            };
        } else {
            return player -> {
                if (!player.inventory.contains(requirement)) {
                    throw new Exception("You don't have the " + requirement + "!");
                }
            };
        }
    }

    public static IReward parseReward(String rewardString) {
        if (rewardString.contains("+") || rewardString.contains("-")) {
            String[] parts = rewardString.split("[+-]");
            String attribute = parts[0];
            int value = Integer.parseInt(parts[1]);

            if (rewardString.contains("+")) {
                return player -> player.addAttribute(attribute, value);
            } else {
                return player -> player.addAttribute(attribute, -value);
            }
        } else {
            return player -> player.inventory.add(rewardString);
        }
    }

    public static void playGame(Player player, DialogueTree dialogueTree) {
        DialogueStep currentStep = dialogueTree.root;

        Scanner scanner = new Scanner(System.in);

        while (currentStep != null) {
            System.out.println("Step ID: " + currentStep.id);
            System.out.println("NPC: " + currentStep.npcReply);
            System.out.println("Player: " + player.HP + " HP, " + player.STR + " STR, " + player.CHA + " CHA, " + player.GOLD + " GOLD");
            System.out.println("Inventory: " + player.inventory);

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
                for (IRequirement requirement : selectedOption.getRequirementModifiers()) {
                    requirement.take(player);
                }

                for (IReward reward : selectedOption.getRewardModifiers()) {
                    reward.reward(player);
                }

                currentStep = selectedOption.getNextStep();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("bro ur ded lol gg no re m8 u suk lol gg ez clap or just an err");
    }
}
