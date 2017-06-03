package fuzzy;

import fuzzy.expressions.*;
import fuzzy.functions.Trapmf;
import fuzzy.functions.Trimf;
import org.junit.Test;

import java.util.Arrays;
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

        Input service = new Input("Service");
        service.addMFnc("Poor", new Trapmf(-0.1, 0, 0.1, 0.3));
        service.addMFnc("Good", new Trimf(0., 0.5, 1.));
        service.addMFnc("Excellent", new Trapmf(0.6, 0.8, 1., 1.1));

        Input food = new Input("Food");
        food.addMFnc("Rancid", new Trapmf(0.1, 0, 1., 3.));
        food.addMFnc("Delicious", new Trapmf(7., 8., 10., 11.));

        Output tip = new Output("Tip");

        Logic l = new Logic();
        l.addRule(new Or(new V(service, "Poor"), new V(food, "Rancid")), Collections.singletonList(new R(tip, "Cheap")));
        l.addRule(new V(service, "Good"), Collections.singletonList(new R(tip, "Average")));
        l.addRule(new Or(new V(service, "Excellent"), new V(food, "Delicious")), Collections.singletonList(new R(tip,"Generous")));

        Map<String, Double> map = new HashMap<>();
        map.put("Service", 0.9);
        map.put("Food", 3.);
        System.out.println(l.getElements(map));

    }

}