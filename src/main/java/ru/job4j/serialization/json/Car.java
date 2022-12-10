package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean ride;
    private int speed;
    private Number number;
    private String[] drivers;

    public Car() {
    }

    public Car(boolean ride, int speed, Number number, String[] drivers) {
        this.ride = ride;
        this.speed = speed;
        this.number = number;
        this.drivers = drivers;
    }

    public boolean isRide() {
        return ride;
    }

    public void setRide(boolean ride) {
        this.ride = ride;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public String[] getDrivers() {
        return drivers;
    }

    public void setDrivers(String[] drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Car{"
                + "ride=" + ride
                + ", speed=" + speed
                + ", number=" + number
                + ", drivers=" + Arrays.toString(drivers)
                + '}';
    }
}
