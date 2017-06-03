package fuzzy.functions;

/**
 * Created by wojciech on 02.06.17.
 */
public class Trapmf implements Function {

    private final double a, bma, dmc, d;

    public Trapmf(double a, double b, double c, double d) {
        this.a = a;
        this.bma = b - a;
        this.dmc = d - c;
        this.d = d;
    }


    @Override
    public double getProbability(double x) {
        final double t1 = (x - a) / bma;
        final double t2 = (d - x) / dmc;
        return Math.max(Math.min(Math.min(t1, t2), 1.), 0.);
    }

}
