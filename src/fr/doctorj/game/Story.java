package fr.doctorj.game;

/**
 * Created by Sirus on 05/05/2014.
 */
public class Story {

    String instruction;
    String help;

    public void setInstruction(String instruction) {
        this.instruction = "Cet homme vient de faire un arrêt cardiaque ! Il faut utiliser le défibrillateur. " +
                "Fais 30 massages cardiaques et 2 insufflations puis un petit coup de défibrillateur. " +
                "C'est parti mon kiki !!!";
    }

    public String getInstruction() {
        return instruction;
    }

    public void setHelp(String help) {
        this.help = "Une variable est une information temporaire qu'on stocke dans la RAM.\n" +
                "Il faut déclarer la variable en écrivant son type et son nom.\n" +
                "Ensuite, nous devons l'initialiser en entrant une valeur.";
    }


    public String getHelp() {
        return help;
    }
}
