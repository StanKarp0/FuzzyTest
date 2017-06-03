package fuzzy;

import fuzzy.functions.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public class Variable {

    private final String name;
    private final Map<String, Function> mFnc;

    Variable(String name) {
        this.name = name;
        this.mFnc = new HashMap<>();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void addMFnc(String value, Function fnc) {
        mFnc.put(value, fnc);
    }

    public double defuzzification(Map<String, Double> wages) {
        System.out.println(wages);
        // TODO defuzzification
        return 0.;
    }

    public double fuzzification(double x, String value) {
        return mFnc.containsKey(value) ? mFnc.get(value).getProbability(x) : 0.0;
    }

}
