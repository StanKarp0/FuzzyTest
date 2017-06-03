package fuzzy;

import fuzzy.functions.InputFunction;

import java.util.*;

/**
 * Created by wojciech on 02.06.17.
 */
public class Input extends Variable {

    private final Map<String, InputFunction> mFnc;

    public Input(String name) {
        super(name);
        this.mFnc = new HashMap<>();
    }

    public void addMFnc(String value, InputFunction fnc) {
        mFnc.put(value, fnc);
    }

    public double getProbability(double x, String value) {
        return mFnc.containsKey(value) ? mFnc.get(value).getProbability(x) : 0.0;
    }

}
