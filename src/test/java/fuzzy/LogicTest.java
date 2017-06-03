package fuzzy;

import fuzzy.expressions.*;
import fuzzy.functions.Trapmf;
import fuzzy.functions.Trimf;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wojciech on 03.06.17.
 */
public class LogicTest {
    @Test
    public void addRule() throws Exception {
    }

    @Test
    public void getElement() throws Exception {

        Variable service = new Variable("Service", 0., 1.);
        service.addMFnc("Poor", new Trapmf(-0.1, 0, 0.1, 0.3));
        service.addMFnc("Good", new Trimf(0., 0.5, 1.));
        service.addMFnc("Excellent", new Trapmf(0.6, 0.8, 1., 1.1));

        Variable food = new Variable("Food", 0., 10.);
        food.addMFnc("Rancid", new Trapmf(-0.1, 0, 1., 3.));
        food.addMFnc("Delicious", new Trapmf(7., 8., 10., 11.));

        Variable tip = new Variable("Tip", 0., 30.);
        tip.addMFnc("Cheap", new Trimf(0., 5., 10.));
        tip.addMFnc("Average", new Trimf(10., 15., 20.));
        tip.addMFnc("Generous", new Trimf(20., 25., 30.));

        Logic l = new Logic();

        l.addRule(new Or(new V(service, "Poor"), new V(food, "Rancid")),
                new R(tip, "Cheap"));
        l.addRule(new V(service, "Good"),
                new R(tip, "Average"));
        l.addRule(new Or(new V(service, "Excellent"), new V(food, "Delicious")),
                new R(tip,"Generous"));



        for(double s = 0; s <= 1; s+= 0.02){
            for(double f = 0.; f <= 10.; f += 0.2) {
                Map<String, Double> map = new HashMap<>();
                map.put("Service", s);
                map.put("Food", f);
                System.out.println(s + " " + f + " " + l.getResults(map).get("Tip"));
            }
        }


    }

}