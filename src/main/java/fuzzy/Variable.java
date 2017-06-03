package fuzzy;

/**
 * Created by wojciech on 03.06.17.
 */
public abstract class Variable {

    private final String name;

    Variable(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

}
