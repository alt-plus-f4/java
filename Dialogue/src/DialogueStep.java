import java.util.ArrayList;
import java.util.List;

class DialogueStep {
    String id;
    String npcReply;
    List<DialogueOption> playerOptions;
    DialogueStep nextStep;

    public DialogueStep(String id, String npcReply, DialogueStep nextStep) throws IllegalArgumentException {
        if ((nextStep != null && !npcReply.isEmpty()) || (playerOptions != null && !playerOptions.isEmpty()))
            throw new IllegalArgumentException("Invalid constructor arguments: Both playerOptions and nextStep cannot be set simultaneously.");

        if (npcReply.isEmpty())
            throw new IllegalArgumentException("NPC reply cannot be empty.");

        this.id = id;
        this.npcReply = npcReply;
        this.playerOptions = new ArrayList<>();
        this.nextStep = nextStep;
    }

    public void setNextStep(DialogueStep nextStep) {
        this.nextStep = nextStep;
    }

    public void addPlayerOption(DialogueOption option) {
        playerOptions.add(option);
    }
}