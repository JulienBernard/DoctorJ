package fr.intechinfo.doctorj.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 08/07/2014.
 */
public class Docteur {
    public static List<Object> paroles = new ArrayList<>();

    public static void parler(Object o) {
        paroles.add(o);
    }

    public static void vider() {
        paroles.clear();
    }
}
