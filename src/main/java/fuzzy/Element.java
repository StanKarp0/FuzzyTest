package fuzzy;

/**
 * Created by wojciech on 02.06.17.
 */
public class Element {

    private final String value;
    private final double prob;
    private final Variable var;

    public Element(Variable var, String value, double prob) {
        this.var = var;
        this.value = value;
        this.prob = prob;
    }

    public double getProb() {
        return prob;
    }

    public String getValue() {
        return value;
    }

    public Variable getVariable() {
        return var;
    }

    @Override
    public String toString() {
        return var + ": "+value + " / " + prob;
    }
}
