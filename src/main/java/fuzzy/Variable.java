package fuzzy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public class Variable {

    private final String name;
    private final Map<String, FFunction> mFnc;
    private final double max, min;
    private final static double N_INT = 5000;

    Variable(String name, double min, double max) {
        this.name = name;
        this.mFnc = new HashMap<>();
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void addMFnc(String value, FFunction fnc) {
        mFnc.put(value, fnc);
    }


    public double defuzzification(Map<String, Double> wages) {
        java.util.function.Function<Double, Double> getMax = (Double x) -> {
            double tempMax = 0.;
            for (Map.Entry<String, FFunction> entry : mFnc.entrySet()) {
                if(wages.containsKey(entry.getKey())) {
                    double value = entry.getValue().apply(x) * wages.get(entry.getKey());
                    if (tempMax < value)
                        tempMax = value;
                }
            }
            return tempMax;
        };

        double dx = (max - min)/N_INT;
        double sumX = 0., sum = 0.;
        double f = getMax.apply(min), f2;
        for(double x = min; x <= max; x += dx) {
            f2 = getMax.apply(x);
            final double a = (f + f2)*(dx/2.);
            sumX += x * a;
            sum += a;
            f = f2;
        }
        return sumX/sum;
    }

    public double fuzzification(double x, String value) {
        return mFnc.containsKey(value) ? mFnc.get(value).apply(x) : 0.0;
    }

}
