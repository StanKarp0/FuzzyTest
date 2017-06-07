package management;

import model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by wojciech on 02.06.17.
 */
public class MockCarDao extends CarDao {

    private Car random() {
        return new Car(
                "random",
                Math.random() * 400,
                Math.random() * 300,
                Math.random() * 100000,
                Math.random() * 20);
    }

    @Override
    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        list.add(new Car("Ford guwno wort", 100., 180, 10000, 10));
        list.add(new Car("Porsze nie gorsze", 400., 270, 300000, 2));
        list.add(new Car("bosze jaki suv", 340., 210, 240000, 3));
        list.add(new Car("pane bmw", 140., 230, 15000, 4));
        list.add(new Car("Matizik", 60., 130, 5000, 14));
        list.add(new Car("Fiacik", 80, 140, 8000, 8));
        list.add(new Car("Toyotoya", 70., 160, 7000, 7));
        list.add(new Car("Pazda", 140., 160, 14000, 4));
        for(int i = 0; i < 50; i++)
            list.add(random());
        return list;
    }
}
