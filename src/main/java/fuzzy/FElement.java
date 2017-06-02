package fuzzy;

import fuzzy.functions.FInputFunction;

/**
 * Created by wojciech on 02.06.17.
 */
public class FElement {

    private final String value;
    private final double prob;

    public FElement(String value, double prob) {
        this.value = value;
        this.prob = prob;
    }

    public double getProb() {
        return prob;
    }

    public String getValue() {
        return value;
    }
}
