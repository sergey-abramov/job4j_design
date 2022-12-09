package ru.job4j.serialization.json;

public class Number {

    private String number;

    public Number(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Number{"
                + "number='" + number + '\''
                + '}';
    }
}
