package management;

import model.Car;

import java.util.List;

/**
 * Created by wojciech on 02.06.17.
 */
abstract public class CarDao {

    abstract public List<Car> findAll();

}
