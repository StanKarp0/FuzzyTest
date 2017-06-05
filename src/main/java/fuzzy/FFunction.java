package fuzzy;

/**
 * Created by wojciech on 02.06.17.
 */
@FunctionalInterface
public interface FFunction {

    double apply(double v);

    static FFunction trapmf(double a, double b, double c, double d) {
        final double bma = b - a, dmc = d - c;
        return x -> Math.max(Math.min(Math.min((x - a) / bma, (d - x) / dmc), 1.), 0.);
    }

    static FFunction trimf(double a, double b, double c) {
        final double bma = b - a, cmb = c - b;
        return x -> Math.max(Math.min((x - a) / bma, (c - x) / cmb), 0.);
    }

}
