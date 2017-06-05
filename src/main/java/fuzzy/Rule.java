package fuzzy;

import java.util.Map;

/**
 * Created by wojciech on 05.06.17.
 */
@FunctionalInterface
public interface Rule {

    Map<String, Double> apply(Map<String, Double> args);

}
