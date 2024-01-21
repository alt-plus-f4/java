public class Main {
    public static void main(String[] args) {
        String input = "1 2 3 * +";
        Calculator calc = new Calculator(input);
        System.out.println(calc.evaluate());
    }
}