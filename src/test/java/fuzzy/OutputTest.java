package fuzzy;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static fuzzy.FFunction.*;

/**
 * Created by wojciech on 03.06.17.
 */
public class OutputTest {
    @Test
    public void addRule() throws Exception {
    }

    @Test
    public void getElement() throws Exception {

        Variable service = new Variable("Service", 0., 1.);
        service.addMFnc("Poor", trapmf(-0.1, 0, 0.1, 0.3));
        service.addMFnc("Good", trimf(0., 0.5, 1.));
        service.addMFnc("Excellent", trapmf(0.6, 0.8, 1., 1.1));

        Variable food = new Variable("Food", 0., 10.);
        food.addMFnc("Rancid", trapmf(-0.1, 0, 1., 3.));
        food.addMFnc("Delicious", trapmf(7., 8., 10., 11.));

        Variable tip = new Variable("Tip", 0., 30.);
        tip.addMFnc("Cheap", trimf(0., 5., 10.));
        tip.addMFnc("Average", trimf(10., 15., 20.));
        tip.addMFnc("Generous", trimf(20., 25., 30.));

        Output outputTip = tip.asOutput();

        outputTip.addRule(service.eq("Poor").or(food.eq( "Rancid")).then("Cheap"));
        outputTip.addRule(service.eq("Good").then("Average"));
        outputTip.addRule(service.eq("Excellent").or(food.eq("Delicious")).then("Generous"));

        for(double s = 0; s <= 1; s+= 0.02){
            for(double f = 0.; f <= 10.; f += 0.2) {
                Map<String, Double> map = new HashMap<>();
                map.put("Service", s);
                map.put("Food", f);
                System.out.println(s + " " + f + " " + outputTip.crisp(map));
            }
        }
    }

    @Test
    public void getElement2() throws Exception {

        Variable service = new Variable("Service", 0., 1.);
        service.addMFnc("Poor", trapmf(-0.1, 0, 0.1, 0.3));
        service.addMFnc("Good", trimf(0., 0.5, 1.));
        service.addMFnc("Excellent", trapmf(0.6, 0.8, 1., 1.1));

        Variable food = new Variable("Food", 0., 10.);
        food.addMFnc("Rancid", trapmf(-0.1, 0, 1., 3.));
        food.addMFnc("Delicious", trapmf(7., 8., 10., 11.));

        Variable tip = new Variable("Tip", 0., 30.);
        tip.addMFnc("Cheap", trimf(0., 5., 10.));
        tip.addMFnc("Average", trimf(10., 15., 20.));
        tip.addMFnc("Generous", trimf(20., 25., 30.));

        Output outputTip = tip.asOutput();

        outputTip.addRule(service.eq("Poor").or(food.eq( "Rancid")).then("Cheap"));
        outputTip.addRule(service.eq("Good").then("Average"));
        outputTip.addRule(service.eq("Excellent").or(food.eq("Delicious")).then("Generous"));

        Map<String, Double> input = new HashMap<>();
        input.put("Service", 0.3);
        input.put("Food", 9.);
        Map<String, Double> output = outputTip.fuzzy(input);
        Assert.assertTrue(Math.abs(output.get("Generous")-1.0) < 1e-5);
        Assert.assertTrue(Math.abs(output.get("Average")-0.6) < 1e-5);
        Assert.assertTrue(Math.abs(output.get("Cheap")-0.0) < 1e-5);
        Assert.assertTrue(Math.abs(outputTip.crisp(input)-21.252994439047594) < 1e-5);

    }

}