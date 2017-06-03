package fuzzy.expressions;

import fuzzy.Variable;

import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public class V extends Expression {

    private final Variable var;
    private final String value;

    public V(Variable var, String value) {
        this.var = var;
        this.value = value;
    }


    @Override
    public double getProbability(Map<String, Double> args) {
        return args.containsKey(var.getName()) ?
                var.fuzzification(args.get(var.getName()), value) :
                0.0;
    }
}
