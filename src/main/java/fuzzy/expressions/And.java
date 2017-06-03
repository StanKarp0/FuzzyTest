package fuzzy.expressions;

import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public class And extends Expression {

    private final Expression e1;
    private final Expression e2;

    public And(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public double getProbability(Map<String, Double> args) {
        return Math.min(e1.getProbability(args), e2.getProbability(args));
    }

}
