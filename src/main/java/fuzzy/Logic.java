package fuzzy;

import java.util.*;

/**
 * Created by wojciech on 03.06.17.
 */
public class Logic {

    private final List<Rule> rules;

    public Logic() {
        this.rules = new LinkedList<>();
    }

    public void addRule(Expression e, String value) {
        rules.add(rule(e, value));
    }

    public Map<String, Double> results(Map<String, Double> args) {

        Map<String, Double> results = new HashMap<>();
        for (Rule r : rules) {
            for (Map.Entry<String, Double> entry : r.apply(args).entrySet()) {
                double m = Math.max(results.getOrDefault(entry.getKey(), 0.), entry.getValue());
                results.put(entry.getKey(), m);
            }
        }
        return results;
    }

    @FunctionalInterface
    private interface Rule {
        Map<String, Double> apply(Map<String, Double> args);
    }

    private Rule rule(Expression e, String value) {
        return args -> {
            Map<String, Double> res = new HashMap<>();
            res.put(value, e.apply(args));
            return res;
        };
    }
}