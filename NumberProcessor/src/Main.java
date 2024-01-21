import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer[]> map = NumberProcessor
                .fromArray(new int[]{1, 2, 3, 4, 5})
                .map(new SingleArgWithResult() {
                    @Override
                    public int process(int arg) {
                        return arg * 2;
                    }
                })
                .filter(new SingleArgWithResult() {
                    @Override
                    public int process(int arg) {
                        return arg % 3 == 0 ? 1 : 0;
                    }
                })
                .groupBy(new SingleArgWithResult() {
                    @Override
                    public int process(int arg) {
                        return arg % 2;
                    }
                });

        map.forEach((key, value) ->
            System.out.println(key + "=" + Arrays.toString(value))
        );

    }
}