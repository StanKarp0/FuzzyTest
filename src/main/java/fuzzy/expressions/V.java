package fuzzy.expressions;

import fuzzy.Input;

import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public class V extends Expression {

    private final Input var;
    private final String value;

    public V(Input var, String value) {
        this.var = var;
        this.value = value;
    }


    @Override
    public double getProbability(Map<String, Double> args) {
        return args.containsKey(var.getName()) ?
                var.getProbability(args.get(var.getName()), value) :
                0.0;
    }
}
