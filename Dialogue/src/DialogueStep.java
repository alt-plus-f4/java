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

    public String getId() {
        return id;
    }

    public String getNpcReply() {
        return npcReply;
    }

    public DialogueStep getNextStep() {
        return nextStep;
    }

    public void setNextStep(DialogueStep nextStep) {
        this.nextStep = nextStep;
    }

    public void setNpcReply(String npcReply) {
        this.npcReply = npcReply;
    }

    public void addPlayerOption(DialogueOption option) {
        playerOptions.add(option);
    }

    public List<DialogueOption> getPlayerOptions() {return playerOptions;}

    @Override
    public String toString() {
        return "DialogueStep{" +
                "id='" + id + '\'' +
                ", npcReply='" + npcReply + '\'' +
                '}';
    }
}