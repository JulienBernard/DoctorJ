package fr.intechinfo.doctorj.model.validators;

import fr.intechinfo.doctorj.model.ApplicationContext;
import fr.intechinfo.doctorj.model.tests.TestListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Verifies that a code source passes a test.
 */
public class TestValidator {
    public static ValidatorMessage check(String storyShortName, String stepShortName) {
        String strPath = ApplicationContext.getInstance().getStoriesPath();

        File file = new File(strPath + "/" + storyShortName);
        String testFileName = stepShortName + "Test";

        try {
            ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()});

            try {
                Class cls = cl.loadClass(testFileName);

                JUnitCore runner = new JUnitCore();
                TestListener l = new TestListener();
                runner.addListener(l);
                Result r = runner.run(cls);

                if(r.wasSuccessful()) {
                    List<ValidatorMessageElement> elements = new ArrayList<>();

                    elements.add(new ValidatorMessageElement("Le code est conforme aux spécifications", ValidatorConstants.TITLE));
                    elements.add(new ValidatorMessageElement("Statistiques :", ValidatorConstants.INFO));
                    elements.add(new ValidatorMessageElement(r.getRunCount() + " test(s) exécuté(s)", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("Temps d'exécution : " + r.getRunTime() + " ms", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("", ValidatorConstants.BLANK));

                    return new ValidatorMessage(true, elements);
                }
                else {
                    List<ValidatorMessageElement> elements = new ArrayList<>();

                    elements.add(new ValidatorMessageElement("Le code n'est pas conforme aux spécifications", ValidatorConstants.TITLE));
                    elements.add(new ValidatorMessageElement("Statistiques :", ValidatorConstants.INFO));
                    elements.add(new ValidatorMessageElement(r.getRunCount() + " test(s) exécuté(s)", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement(r.getFailureCount() + " erreur(s)", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("Temps d'exécution : " + r.getRunTime() + " ms", ValidatorConstants.BLANK));
                    elements.add(new ValidatorMessageElement("Liste des erreurs :", ValidatorConstants.SUBTITLE));

                    for(Failure f : r.getFailures()) {
                        elements.add(new ValidatorMessageElement("\tTest : " + f.getDescription(), ValidatorConstants.ERROR));
                        elements.add(new ValidatorMessageElement("\t\tErreur : " + f.getMessage().replace("<[", " <<").replace("]>", ">> "), ValidatorConstants.BLANK));
                    }

                    return new ValidatorMessage(false, elements);
                }
            }
            catch(ClassNotFoundException e) {
                e.printStackTrace();

                List<ValidatorMessageElement> elements = new ArrayList<>();
                elements.add(new ValidatorMessageElement("Erreur système", ValidatorConstants.TITLE));
                elements.add(new ValidatorMessageElement("La classe de test n'existe pas !", ValidatorConstants.ERROR));

                return new ValidatorMessage(false, elements);
            }
        }
        catch(MalformedURLException e) {
            e.printStackTrace();

            List<ValidatorMessageElement> elements = new ArrayList<>();
            elements.add(new ValidatorMessageElement("Erreur système", ValidatorConstants.TITLE));
            elements.add(new ValidatorMessageElement("Le chemin vers la classe de test est invalide !", ValidatorConstants.ERROR));

            return new ValidatorMessage(false, elements);
        }
    }
}