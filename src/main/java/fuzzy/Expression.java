package fuzzy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
@FunctionalInterface
public interface Expression {

    double apply(Map<String, Double> args);

    default Rule then(String value) {
        return args -> {
            Map<String, Double> res = new HashMap<>();
            res.put(value, apply(args));
            return res;
        };
    }

    static Expression or(Expression e1, Expression e2) {
        return args -> Math.max(e1.apply(args), e2.apply(args));
    }

    static Expression and(Expression e1, Expression e2) {
        return args -> Math.min(e1.apply(args), e2.apply(args));
    }

}
