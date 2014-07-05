package fr.intechinfo.doctorj.utils;

import java.io.*;

/**
 * Created by Alexandre on 05/07/2014.
 */
public class Serialization {
    public static <T> T loadFile(String filePath) {
        ObjectInputStream ois = null;
        try {
            FileInputStream fichier = new FileInputStream(filePath);
            ois = new ObjectInputStream(fichier);
            Object loadedFile = ois.readObject();

            return (T) loadedFile;
        }
        catch (final java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (final ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static <T> boolean saveFile(T obj, String filePath) {
        ObjectOutputStream oos = null;

        try {
            FileOutputStream fichier = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(obj);
            oos.flush();

            return true;
        }
        catch (final java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
