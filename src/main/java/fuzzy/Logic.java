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

    public void addRule(Rule r) {
        rules.add(r);
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
}