package fuzzy;

import fuzzy.functions.FInputFunction;

import java.util.*;

/**
 * Created by wojciech on 02.06.17.
 */
public class FInVariable {

    private final String name;
    private final List<FuncTuple> mFun;

    public FInVariable(String name) {

        this.name = name;
        this.mFun = new LinkedList<>();
    }

    public void addMFnc(String value, FInputFunction fnc) {
        mFun.add(new FuncTuple(value, fnc));
    }

    public FElement getElement(double x) {
        return mFun.stream().max(Comparator.comparingDouble(a -> a.fnc.getProbability(x)))
                .map(t -> new FElement(t.value, t.fnc.getProbability(x)))
                .orElseGet(() -> new FElement("None", 0.));
    }

    @Override
    public String toString() {
        return "Variable: "+name;
    }

    private class FuncTuple {
        final String value;
        final FInputFunction fnc;
        FuncTuple(String value, FInputFunction fnc) {
            this.value = value;
            this.fnc = fnc;
        }
    }

}
