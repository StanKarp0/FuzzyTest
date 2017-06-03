package fuzzy;

import fuzzy.expressions.Expression;
import fuzzy.functions.OutputFunction;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by wojciech on 03.06.17.
 */
public class Logic {

    final List<Rule> rules;

    public Logic() {
        this.rules = new LinkedList<>();
    }

    public void addRule(Expression e, List<R> values) {
        rules.add(new Rule(e, values));
    }

    public List<Element> getElements(Map<String, Double> map) {
        List<Element> result = new ArrayList<>();
        for(Rule r: rules){
            result.addAll(r.getElements(map));
        }
//        return rules.stream().map(r -> r.getElements(map)).collect(Collectors);
        return result;
    }


    private static class Rule {

        private final Expression e;
        private final List<R> values;

        Rule(Expression e, List<R> values) {
            this.e = e;
            this.values = values;
        }

        public List<Element> getElements(Map<String, Double> args) {
            List<Element> result = new ArrayList<>();
            for (R r: values) {
                result.add(new Element(r.getOutput(), r.getValue(), e.getProbability(args)));
            }
            return result;
        }
    }

}
