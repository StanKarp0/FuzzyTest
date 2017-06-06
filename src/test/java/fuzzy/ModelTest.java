package fuzzy;

import management.MockCarDao;
import model.Car;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by wojciech on 06.06.17.
 */
public class ModelTest {

    @Test
    public void addVariable() throws Exception {
        Model<Car> m = new Model<>();

        Variable price = new Variable("price", 0., 1000000);
        price.addMFnc("low", FFunction.trapmf(-1, 0, 5000,10000));
        price.addMFnc("medium", FFunction.trapmf(6000, 7500, 35000,40000));
        price.addMFnc("high", FFunction.trapmf(30000, 38000, 1000000,1000001));

        m.addVariable(price, Car::getCost);

        List<Car> cars = new MockCarDao().findAll();
        for(Car c: cars) {
            Assert.assertEquals(price.fuzzification(c.getCost()), m.getArgs(c, "price"));
        }
    }

    @Test
    public void addRelation() throws Exception {
        Model<Car> m = new Model<>();

        Variable maxSpeed = new Variable("max_speed", 0., 500.);
        maxSpeed.addMFnc("slow", FFunction.trapmf(-1, 0, 110, 130));
        maxSpeed.addMFnc("average", FFunction.trapmf(120, 130, 160, 200));
        maxSpeed.addMFnc("fast", FFunction.trapmf(180, 210, 500, 501));

        Variable power = new Variable("power", 0., 500.);
        power.addMFnc("slow", FFunction.trapmf(-1, 0, 60, 90));
        power.addMFnc("average", FFunction.trapmf(80, 100, 150, 200));
        power.addMFnc("fast", FFunction.trapmf(180, 210, 500, 501));

        Variable speed = new Variable("speed", 0., 10.);
        speed.addMFnc("slowS", FFunction.trapmf(-1, 0, 2, 3));
        speed.addMFnc("averageS", FFunction.trapmf(1, 3, 6, 8));
        speed.addMFnc("fastS", FFunction.trapmf(7, 9, 10, 11));

        Output outSpeed = speed.asOutput();
        outSpeed.addRule(maxSpeed.eq("slow").or(power.eq("slow")).then("slowS"));
        outSpeed.addRule(maxSpeed.eq("average").then("averageS"));
        outSpeed.addRule(maxSpeed.eq("fast").or(power.eq("fast")).then("fastS"));

        m.addRelation(outSpeed, c -> {
            Map<String, Double> input = new HashMap<>();
            input.put("max_speed", c.getMaxSpeed());
            input.put("power", c.getPower());
            return input;
        });

        List<Car> cars = new MockCarDao().findAll();
        for(Car c: cars) {
            Map<String, Double> input = new HashMap<>();
            input.put("max_speed", c.getMaxSpeed());
            input.put("power", c.getPower());
            Assert.assertEquals(outSpeed.fuzzy(input), m.getArgs(c, "speed"));
        }
    }

    @Test
    public void getArgs() throws Exception {



    }

}