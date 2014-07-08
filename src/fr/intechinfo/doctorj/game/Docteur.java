package fr.intechinfo.doctorj.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 08/07/2014.
 */
public class Docteur {
    public static List<Object> Paroles = new ArrayList<>();

    public static void Parler(Object o) {
        Paroles.add(o);
    }

    public static void Vider() {
        Paroles.clear();
    }
}
