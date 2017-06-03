package fuzzy;

import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
@FunctionalInterface
public interface Expression {

    double apply(Map<String, Double> args);

    static Expression v(Variable var, String value) {
        return args -> args.containsKey(var.getName()) ?
                var.fuzzification(args.get(var.getName()), value) : 0.0;
    }

    static Expression or(Expression e1, Expression e2) {
        return args -> Math.max(e1.apply(args), e2.apply(args));
    }

    static Expression and(Expression e1, Expression e2) {
        return args -> Math.min(e1.apply(args), e2.apply(args));
    }

    static OutR r(Variable var, String value) {
        return new OutR(var, value);
    }

}
