package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User one = new User("Sergey", 12, birthday);
        int hashCode = one.hashCode();
        int hashOne = hashCode ^ (hashCode >>> 16);
        int bucketOne = hashOne & 15;
        User two = new User("Sergey", 12, birthday);
        int hashCode2 = one.hashCode();
        int hashTwo = hashCode2 ^ (hashCode2 >>> 16);
        int bucketTwo = hashTwo & 15;
        map.put(one, new Object());
        map.put(two, new Object());
        System.out.printf("User one - %S; hash - %S; bucket - %S; ",
                hashCode, hashOne, bucketOne);
        System.out.printf("User two - %S; hash - %S; bucket - %S; ",
                hashCode2, hashTwo, bucketTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
