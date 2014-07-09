package fr.intechinfo.doctorj.game;

/**
 * Created by Alexandre on 08/07/2014.
 */
public class Patient {
    public static String nom;
    public static String prenom;
    public static int age;
    public static double taille;
    public static double poids;
    public static double temperature;
    public static int pouls;
    public static int pression;
    public static String[] virus;

    public static void vider() {
        nom = null;
        prenom = null;
        age = 0;
        taille = 0;
        poids = 0;
        temperature = 0;
        pouls = 0;
        pression = 0;
        virus = null;
    }
}
