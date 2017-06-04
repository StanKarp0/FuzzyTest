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
        rules.add(new Rule(e, Arrays.asList(values)));
    }

    public Map<String, Double> results(Map<String, Double> map) {

        List<Element> elements = new ArrayList<>();

        for(Rule r: rules)
            elements.addAll(r.getElements(map));

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


    private class Rule {

        private final Expression e;
        private final List<OutR> values;

        Rule(Expression e, List<OutR> values) {
            this.e = e;
            this.values = values;
        }

        public List<Element> getElements(Map<String, Double> args) {
            List<Element> result = new ArrayList<>();
            for (OutR r: values) {
                result.add(new Element(r.getVariable(), r.getValue(), e.apply(args)));
            }
            return result;
        }
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
