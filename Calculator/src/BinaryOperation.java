import java.util.Stack;

public abstract class BinaryOperation extends Operation {

    @Override
    void perform(Stack<Float> stack) {
        stack.push(this.perform(stack.pop(), stack.pop()));
    }

    abstract float perform(float a, float b);
}
