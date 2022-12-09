package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Car car = new Car(true, 160, new Number("021"),
                new String[] {"Ivan, Vladimir, Nickolas"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
               "{"
                       + "\"ride\":false,"
                       + "\"speed\":0,"
                       + "\"number\":"
                       + "{"
                       + "\"number\":\"093\""
                       + "},"
                       + "\"drivers\":"
                       + "[\"Sergey\",\"Oleg\"]"
                       + "}";
        final Car car1 = gson.fromJson(carJson, Car.class);
        System.out.println(car1);
    }
}
