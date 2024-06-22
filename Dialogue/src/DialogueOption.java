import java.util.ArrayList;
import java.util.List;

class DialogueOption {
    String playerReply;
    DialogueStep nextStep;
    List<IOptional> optionalModifiers;
    List<IReward> rewardModifiers;
    List<IRequirement> requirementModifiers;

    public DialogueOption(String playerReply, DialogueStep nextStep) {
        this.playerReply = playerReply;
        this.nextStep = nextStep;
        this.optionalModifiers = new ArrayList<>();
        this.rewardModifiers = new ArrayList<>();
        this.requirementModifiers = new ArrayList<>();
    }

    public void addOptionalModifier(IOptional modifier) {
        optionalModifiers.add(modifier);
    }
    public void addRewardModifier(IReward modifier) {
        rewardModifiers.add(modifier);
    }
    public void addRequirementModifier(IRequirement modifier) {
        requirementModifiers.add(modifier);
    }

    public void setNextStep(DialogueStep nextStep) {
        this.nextStep = nextStep;
    }

    public String getPlayerReply() {
        return playerReply;
    }

    public void setPlayerReply(String playerReply) {
        this.playerReply = playerReply;
    }

    public DialogueStep getNextStep() {
        return nextStep;
    }

    public List<IOptional> getOptionalModifiers() {
        return optionalModifiers;
    }

    public void setOptionalModifiers(List<IOptional> optionalModifiers) {
        this.optionalModifiers = optionalModifiers;
    }

    public List<IReward> getRewardModifiers() {
        return rewardModifiers;
    }

    public void setRewardModifiers(List<IReward> rewardModifiers) {
        this.rewardModifiers = rewardModifiers;
    }

    public List<IRequirement> getRequirementModifiers() {
        return requirementModifiers;
    }

    public void setRequirementModifiers(List<IRequirement> requirementModifiers) {
        this.requirementModifiers = requirementModifiers;
    }

    @Override
    public String toString() {
        return "DialogueOption{" +
                "playerReply='" + playerReply + '\'' +
                ", optionalModifiers=" + optionalModifiers +
                ", rewardModifiers=" + rewardModifiers +
                ", requirementModifiers=" + requirementModifiers +
                '}';
    }
}