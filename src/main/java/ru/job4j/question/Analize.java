package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!map.containsKey(user.getId())
                    && previous.size() == current.size()) {
                rsl.setDeleted(+1);
                rsl.setAdded(+1);
            } else if (map.containsKey(user.getId())
                    && previous.size() == current.size()
                    && !map.containsValue(user.getName())) {
                rsl.setChanged(+1);
            } else if (previous.size() < current.size()) {
                rsl.setAdded(+1);
            } else if (previous.size() > current.size()) {
                rsl.setDeleted(+1);
            }
        }
        return rsl;
    }
}
