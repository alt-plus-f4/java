public class DivideOperator extends BinaryOperation {
    @Override
    public float perform(float a, float b) {
        if(b == 0) throw new IllegalArgumentException();
        return a / b;
    }
}
