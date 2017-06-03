package fuzzy;

/**
 * Created by wojciech on 03.06.17.
 */
public class R {

    private final Variable var;
    private final String value;

    public R(Variable var, String value){

        this.var = var;
        this.value = value;
    }

    @Override
    public String toString() {
        return var + ": " + value;
    }

    public Variable getVariable() {
        return var;
    }

    public String getValue() {
        return value;
    }
}
