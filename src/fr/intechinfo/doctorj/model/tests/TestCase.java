package fr.intechinfo.doctorj.model.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandre on 28/05/2014.
 */
public abstract class TestCase {
    protected static Object testObject;
    protected static Class testClass;
    protected static List<Object> outStream = new LinkedList<>();

    public TestCase() {
        outStream.clear();

        // Contrôle de System.out
        System.setOut(new PrintStream(System.out) {
            public void println(String s) {
                outStream.add(s);
                super.println(s);
            }
        });
    }

    protected static final void setStepToTest(String storyName, String stepName) {
        File file = new File("./stories/" + storyName);

        try {
            ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()});

            try {
                testClass = cl.loadClass(stepName);
            }
            catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected static final TestMethod callMethod(String name) {
        TestMethod tm = new TestMethod(testObject, name);
        return tm;
    }

    @BeforeClass
    // Code exécuté avant l'exécution de tous les tests
    public static void setUpClass() throws Exception {}

    @AfterClass
    // Code exécuté après l'exécution de tous les tests
    public static void tearDownClass() throws Exception {}

    @Before
    // Code exécuté avant chaque test
    public void setUp() throws Exception { testObject = testClass.newInstance(); }

    @After
    // Code exécuté après chaque test
    public void tearDown() throws Exception {}
}
