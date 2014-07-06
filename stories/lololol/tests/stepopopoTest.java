import org.junit.*;
import fr.intechinfo.doctorj.model.tests.*;

public class stepopopoTest extends TestCase {

    @BeforeClass
    // Code exécuté avant l'exécution de tous les tests
    public static void setUpClass() throws Exception {
        setStepToTest("lololol", "stepopopo");
    }

    @AfterClass
    // Code exécuté après l'exécution de tous les tests
    public static void tearDownClass() throws Exception {

    }

    @Before
    // Code exécuté avant chaque test
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    // Code exécuté après chaque test
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
	// Exemple de test d'un getter/setter
    public void exampleTestSetMethod() throws Exception {
        callMethod("setMethod").withParameterTypes(String.class).withParameters("parameter").execute();

        String result = callMethod("getMethod").execute().toString();
        System.out.println(result);

        Assert.assertEquals("parameter", result);
    }
}