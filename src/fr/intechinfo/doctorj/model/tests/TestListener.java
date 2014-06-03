package fr.intechinfo.doctorj.model.tests;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * Created by Alexandre on 28/05/2014.
 */
public class TestListener extends RunListener {
    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println("Le test commence...");

        super.testRunStarted(description);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("Test fini !");
        System.out.println("Fails : " + result.getFailureCount());

        super.testRunFinished(result);
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
    }
}
