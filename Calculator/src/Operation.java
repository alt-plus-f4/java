import java.util.Stack;

public abstract class Operation {
    char sign;

    abstract void perform(Stack<Float> stack);
}
