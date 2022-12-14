package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "number")
@XmlAccessorType(XmlAccessType.FIELD)
public class Number {

    @XmlAttribute
    private String number;

    public Number() {
    }

    public Number(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Number{"
                + "number='" + number + '\''
                + '}';
    }
}
