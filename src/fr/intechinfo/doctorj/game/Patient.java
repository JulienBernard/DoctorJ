package fr.intechinfo.doctorj.game;

/**
 * Created by Alexandre on 08/07/2014.
 */
public class Patient {
    public static String Nom;
    public static String Prenom;
    public static int Age;
    public static double Taille;
    public static double Poids;
    public static double Temperature;
    public static int Pouls;
    public static int Pression;
    public static String[] Virus;

    public static void Vider() {
        Nom = null;
        Prenom = null;
        Age = 0;
        Taille = 0;
        Poids = 0;
        Temperature = 0;
        Pouls = 0;
        Pression = 0;
        Virus = null;
    }
}
