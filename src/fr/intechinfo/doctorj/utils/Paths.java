package fr.intechinfo.doctorj.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alexandre on 05/07/2014.
 */
public class Paths {
    public static String getAppPath() {
        try {
            return new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getStoriesPath() {
        return getAppPath() + "/stories";
    }
}
