package fuzzy.expressions;

import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public abstract class Expression {

    public abstract double getProbability(Map<String, Double> args);

}
