package fuzzy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wojciech on 03.06.17.
 */
public class Logic {

    private final List<Rule> rules;

    public Logic() {
        this.rules = new LinkedList<>();
    }

    public void addRule(Expression e, OutR... values) {
        rules.add(rule(e, Arrays.asList(values)));
    }

    public Map<String, Double> results(Map<String, Double> map) {

        List<Element> elements = new ArrayList<>();

        for(Rule r: rules)
            elements.addAll(r.apply(map));

        Map<String, Double> results = new HashMap<>();
        Map<Variable, List<Element>> collect = elements.stream()
                .collect(Collectors.groupingBy(Element::getVariable));

        for (Map.Entry<Variable, List<Element>> entry : collect.entrySet()) {
            Map<String, Double> wages = new HashMap<>();
            for(Element e: entry.getValue()) {
                if(wages.containsKey(e.getValue())) {
                    if (wages.get(e.getValue()) < e.getProb())
                        wages.put(e.getValue(), e.getProb());
                } else {
                    wages.put(e.getValue(), e.getProb());
                }
            }
            results.put(entry.getKey().getName(), entry.getKey().defuzzification(wages));
        }

        return results;
    }

    @FunctionalInterface
    private interface Rule {
        List<Element> apply(Map<String, Double> args);
    }

    private Rule rule(Expression e, List<OutR> values) {
        return args -> {
            List<Element> result = new ArrayList<>();
            final double prob = e.apply(args);
            for (OutR r: values) {
                result.add(new Element(r.getVariable(), r.getValue(), prob));
            }
            return result;
        };
    }

    private class Element {

        private final String value;
        private final double prob;
        private final Variable var;

        Element(Variable var, String value, double prob) {
            this.var = var;
            this.value = value;
            this.prob = prob;
        }

        double getProb() {
            return prob;
        }

        String getValue() {
            return value;
        }

        Variable getVariable() {
            return var;
        }

        @Override
        public String toString() {
            return var + ": "+value + " / " + prob;
        }
    }

}
