package fuzzy;

/**
 * Created by wojciech on 03.06.17.
 */
public class R {

    private final Output var;
    private final String value;

    public R(Output var, String value){

        this.var = var;
        this.value = value;
    }

    @Override
    public String toString() {
        return var + ": " + value;
    }

    public Output getOutput() {
        return var;
    }

    public String getValue() {
        return value;
    }
}
