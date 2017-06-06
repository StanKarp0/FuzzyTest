package model;

/**
 * Created by wojciech on 02.06.17.
 */
public class Car {

    private final String name;
    private final double power;
    private final double maxSpeed;
    private final double cost;
    private final double years;

    public Car(String name, double power, double maxSpeed, double cost, double years) {
        this.name = name;
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.cost = cost;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getPower() {
        return power;
    }

    public double getCost() {
        return cost;
    }

    public double getYears() {
        return years;
    }

    @Override
    public String toString() {
        return name + " " + cost;
    }


}
