import java.util.ArrayDeque;
import java.util.Queue;

class DialogueTree {
    DialogueStep root;

    public DialogueTree(DialogueStep root) {
        this.root = root;
    }

    public DialogueStep findById(String id) throws Exception {
        Queue<DialogueStep> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            DialogueStep currentStep = queue.poll();

            if (currentStep.id.equals(id)) {
                return currentStep;
            }

            for (DialogueOption option : currentStep.playerOptions) {
                if (option.nextStep != null) {
                    queue.add(option.nextStep);
                }
            }

            if (currentStep.nextStep != null) {
                queue.add(currentStep.nextStep);
            }
        }

        throw new Exception("NOT FOUND!");
    }

    @Override
    public String toString() {
        return "DialogueTree{" +
                "root=" + root +
                '}';
    }
}