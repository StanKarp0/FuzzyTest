package fuzzy;

import management.MockCarDao;
import model.Car;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by wojciech on 06.06.17.
 */
public class ModelBuilderTest {

    @Test
    public void addVariable() throws Exception {
        Model.Builder<Car> m = new Model.Builder<>();

        Variable price = new Variable("price", 0., 500000);
        price.addMFnc("low", FFunction.gaussmf(20000, 0));
        price.addMFnc("medium", FFunction.gaussmf(20000, 40000));
        price.addMFnc("high", FFunction.gaussmf(200000, 500000));

        m.addVariable(price, Car::getCost);

        List<Car> cars = new MockCarDao().findAll();
        List<Model<Car>> models = new LinkedList<>();
        for(Car c: cars) {
            Model<Car> mc = m.get(c);
            models.add(mc);
            Assert.assertEquals(price.fuzzification(c.getCost()), mc.getFuzzy("price"));
        }

        Map<String, Double> userInput = price.fuzzification(37000);
        Model.sort("price", userInput, models);
        models.forEach(a -> System.out.println(" " + a.distance("price", userInput) + " | " + a));
    }

    @Test
    public void addRelation() throws Exception {
        Model.Builder<Car> m = new Model.Builder<>();

        Variable maxSpeed = new Variable("max_speed", 0., 500.);
        maxSpeed.addMFnc("slow", FFunction.trapmf(-1, 0, 110, 130));
        maxSpeed.addMFnc("average", FFunction.trapmf(120, 130, 160, 200));
        maxSpeed.addMFnc("fast", FFunction.trapmf(180, 210, 500, 501));

        Variable power = new Variable("power", 0., 500.);
        power.addMFnc("slow", FFunction.trapmf(-1, 0, 60, 90));
        power.addMFnc("average", FFunction.trapmf(80, 100, 150, 200));
        power.addMFnc("fast", FFunction.trapmf(180, 210, 500, 501));

        Variable speed = new Variable("speed", 0., 10.);
        speed.addMFnc("slowS", FFunction.gaussmf(1.5, 0));
        speed.addMFnc("averageS", FFunction.gaussmf(1.5, 5));
        speed.addMFnc("fastS", FFunction.gaussmf(1.5, 10));

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
        List<Model<Car>> models = new LinkedList<>();
        for(Car c: cars) {
            Map<String, Double> input = new HashMap<>();
            input.put("max_speed", c.getMaxSpeed());
            input.put("power", c.getPower());
            Model<Car> mc = m.get(c);
            Assert.assertEquals(outSpeed.fuzzy(input), mc.getFuzzy("speed"));
            models.add(mc);
        }

        Map<String, Double> userInput = speed.fuzzification(8.);
        System.out.println(userInput);
        Model.sort("speed", userInput, models);
        models.forEach(a -> System.out.println(a.getFuzzy("speed") + "|" + a.distance("speed", userInput) + " | " + speed.defuzzification(a.getFuzzy("speed"))));

    }

    @Test
    public void getArgs() throws Exception {



    }

}