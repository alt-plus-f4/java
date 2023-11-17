import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    ArrayList<Operation> operations = new ArrayList<>();
    Stack<Float> stack = new Stack<>();

    Calculator(String input){
        String[] characters = input.split(" ");

        for(String character: characters){
            System.out.println(character);
            if(character.matches("-?\\d+(\\.\\d+)?"))
                addNumber(character);// if number
            else
                addOperation(character);
        }
    }

    void addOperation(String operation){
        switch(operation){
            case "+":
                operations.add(new PlusOperator());
                break;
            case "-":
                operations.add(new MinusOperator());
                break;
            case "*":
                operations.add(new MultiplyOperator());
                break;
            case "/":
                operations.add(new DivideOperator());
                break;
            default:
                System.out.println("nah");
                break;
        }
    }

    void addNumber(String number){
        stack.push(Float.parseFloat(number));
    }

    float evaluate(){
        while(stack.size() != 1)
            operations.getFirst().perform(stack);

        return stack.pop();
    }

}
