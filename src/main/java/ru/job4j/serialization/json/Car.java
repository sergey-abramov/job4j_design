package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {

    private final boolean ride;
    private final int speed;
    private final Number number;
    private final String[] drivers;

    public Car(boolean ride, int speed, Number number, String[] statuses) {
        this.ride = ride;
        this.speed = speed;
        this.number = number;
        this.drivers = statuses;
    }

    @Override
    public String toString() {
        return "Car{"
                + "ride=" + ride
                + ", speed=" + speed
                + ", number=" + number
                + ", statuses=" + Arrays.toString(drivers)
                + '}';
    }
}
