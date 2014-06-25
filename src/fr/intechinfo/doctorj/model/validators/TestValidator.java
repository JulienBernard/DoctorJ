package fr.intechinfo.doctorj.model.validators;

import fr.intechinfo.doctorj.model.ApplicationContext;
import fr.intechinfo.doctorj.model.tests.TestListener;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
                    StringBuilder sb = new StringBuilder();
                    sb.append("Le code est conforme aux spécifications !");
                    sb.append("\n\n");

                    sb.append("Statistiques :");
                    sb.append('\n');

                    sb.append(r.getRunCount());
                    sb.append(" test(s) exécuté(s)");
                    sb.append('\n');

                    sb.append("Temps d'exécution : ");
                    sb.append(r.getRunTime());
                    sb.append(" ms");
                    sb.append('\n');

                    return new ValidatorMessage(true, sb.toString());
                }
                else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Le code n'est pas conforme aux spécifications !");
                    sb.append("\n\n");

                    sb.append("Statistiques :");
                    sb.append('\n');

                    sb.append(r.getRunCount());
                    sb.append(" test(s) exécuté(s)");
                    sb.append('\n');

                    sb.append(r.getFailureCount());
                    sb.append(" erreur(s)");
                    sb.append('\n');

                    sb.append("Temps d'exécution : ");
                    sb.append(r.getRunTime());
                    sb.append(" ms");
                    sb.append("\n\n");

                    sb.append("Liste des erreurs :");
                    sb.append('\n');

                    for(Failure f : r.getFailures()) {
                        sb.append("- Test : ");
                        sb.append(f.getDescription());
                        sb.append("\n");
                        sb.append("Erreur : ");
                        sb.append(f.getMessage());
                        sb.append("\n");
                    }

                    return new ValidatorMessage(false, sb.toString());
                }
            }
            catch(ClassNotFoundException e) {
                e.printStackTrace();
                return new ValidatorMessage(false, "La classe de test n'existe pas");
            }
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
            return new ValidatorMessage(false, "Le chemin vers la classe de test n'est pas valide");
        }
    }
}