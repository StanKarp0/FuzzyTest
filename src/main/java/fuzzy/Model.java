package fuzzy;

import java.util.*;
import java.util.function.Function;

/**
 * Created by wojciech on 07.06.17.
 */
public class Model<T> {

    private final Map<String, Map<String, Double>> fuzzy;
    private final T t;

    private Model(T t, Map<String, Map<String, Double>> fuzzy) {
        this.fuzzy = fuzzy;
        this.t = t;
    }

    public T get() {
        return t;
    }

    public Map<String, Double> getFuzzy(String value) {
        return fuzzy.getOrDefault(value, new HashMap<>());
    }

    public double distance(String value, Map<String, Double> m2) {
        double sum = 0;
        Map<String, Double> m1 = new HashMap<>(getFuzzy(value));
        m1.replaceAll((v, p) -> Math.pow(m2.containsKey(v) ? (p - m2.get(v)) : 0., 2.));
        for(double p2: m1.values())
            sum += p2;
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return t.toString();
    }

    public static <T> void sort(String value, Map<String, Double> x, List<Model<T>> models) {
        models.sort(Comparator.comparingDouble(a -> a.distance(value, x)));
    }

    public static class Builder<S> {

        final private Map<String, Function<S, Map<String, Double>>> map;

        public Builder() {
            this.map = new HashMap<>();
        }

        public void addVariable(Variable var, Function<S, Double> fnc) {
            map.put(var.getName(), s -> var.fuzzification(fnc.apply(s)));
        }

        public void addRelation(Output out, Function<S, Map<String, Double>> fnc) {
            map.put(out.getName(), s -> out.fuzzy(fnc.apply(s)));
        }

        public Model<S> get(S s) {
            Map<String, Map<String, Double>> res = new HashMap<>();
            for (Map.Entry<String, Function<S, Map<String, Double>>> entry : map.entrySet()) {
                res.put(entry.getKey(), entry.getValue().apply(s));
            }
            return new Model<>(s, res);
        }

        public List<Model<S>> get(List<S> list) {
            List<Model<S>> result = new LinkedList<>();
            for(S s: list)
                result.add(get(s));
            return result;
        }
    }
}
