package fr.intechinfo.doctorj.model.validators;

import fr.intechinfo.doctorj.model.tests.TestListener;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Verifies that a code source passes a test.
 */
public class TestValidator {
    public static ValidatorMessage check(String testFolder, String testName) {
        File file = new File(testFolder);

        try {
            ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()});

            try {
                Class cls = cl.loadClass(testName);

                JUnitCore runner = new JUnitCore();
                //TestListener l = new TestListener();
                //runner.addListener(l);
                runner.addListener(new TextListener(System.out));
                runner.run(cls);

                return null;
            }
            catch(ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}