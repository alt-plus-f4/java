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
}