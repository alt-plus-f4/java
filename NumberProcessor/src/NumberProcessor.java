import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class NumberProcessor {
    Vector <Integer> vector;

    private NumberProcessor(int[] arr) {
        vector = new Vector<>();

        for (int number : arr)
            vector.add(number);
    }

    static NumberProcessor fromArray(int[] arr) {
        return new NumberProcessor(arr);
    }

    public NumberProcessor map(SingleArgWithResult arg) {
        for (int i = 0; i < vec.size(); i++) {
            vec.set(i, arg.process(vec.get(i)));
        }
        return this;
    }
    public NumberProcessor filter(SingleArgWithResult arg) {
        for (int i = 0; i < vec.size(); i++) {
            if(arg.process(vec.get(i)) == 1) {
                vec.removeElement(vec.get(i));
            }
        }
        return this;
    }

    public HashMap<Integer, Integer[]> groupBy(SingleArgWithResult arg) {
        HashMap<Integer, Integer[]> mapReturned = new HashMap<>();
        HashMap<Integer, Vector<Integer>> map = new HashMap<>();

        for (Integer elem : vec) {
            int key = arg.process(elem);
            Vector<Integer> output = map.getOrDefault(key, new Vector<>());
            output.add(elem);
            map.put(key, output);
        }

        map.forEach((k, v) -> {
            Integer[] array = v.toArray(new Integer[0]);
            mapReturned.put(k, array);
        });

        return mapReturned;
    }
}
