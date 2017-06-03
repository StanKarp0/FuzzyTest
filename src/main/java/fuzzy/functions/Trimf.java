package fuzzy.functions;

/**
 * Created by wojciech on 02.06.17.
 */
public class Trimf implements InputFunction, OutputFunction {

    private final double a, bma, cmb, c;

    public Trimf(double a, double b, double c) {
        this.a = a;
        this.bma = b - a;
        this.cmb = c - b;
        this.c = c;
    }

    @Override
    public double getProbability(double x) {
        final double t1 = (x - a) / bma;
        final double t2 = (c - x) / cmb;
        return Math.max(Math.min(t1, t2), 0.);
    }

    @Override
    public double getCrisp(double probability) {
        // TODO reverse or what??
        return 0.;
    }
}
