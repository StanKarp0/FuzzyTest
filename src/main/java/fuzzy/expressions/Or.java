package fuzzy.expressions;

import fuzzy.expressions.Expression;

import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public class Or extends Expression {

    private final Expression e1;
    private final Expression e2;

    public Or(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public double getProbability(Map<String, Double> args) {
        return Math.max(e1.getProbability(args), e2.getProbability(args));
    }
}
