package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    private static  void json() {
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

    private static void xml() throws JAXBException, IOException {
        Car car = new Car(true, 160, new Number("021"),
                new String[] {"Ivan, Vladimir, Nickolas"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car rsl = (Car) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }
    }
    public static void main(String[] args) throws JAXBException, IOException {
       json();
       xml();
    }
}
