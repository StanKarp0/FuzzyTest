package fuzzy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by wojciech on 06.06.17.
 */
public class Model<T> {

    final private Map<String, Function<T, Map<String, Double>>> map;

    public Model() {
        this.map = new HashMap<>();
    }

    public void addVariable(Variable var, Function<T, Double> fnc) {
        map.put(var.getName(), t -> var.fuzzification(fnc.apply(t)));
    }

    public void addRelation(Output out, Function<T, Map<String, Double>> fnc) {
        map.put(out.getName(), t -> out.fuzzy(fnc.apply(t)));
    }

    public Map<String, Double> getArgs(T t, String value) {
        if(map.containsKey(value))
            return map.get(value).apply(t);
        return Collections.emptyMap();
    }

}
