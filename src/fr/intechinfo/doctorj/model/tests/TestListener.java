package fr.intechinfo.doctorj.model.tests;

import fr.intechinfo.doctorj.model.validators.ValidatorMessage;
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
    }

    @Override
    public void testRunFinished(Result result) throws Exception {

    }

    @Override
    public void testStarted(Description description) throws Exception {

    }

    @Override
    public void testFinished(Description description) throws Exception {

    }

    @Override
    public void testFailure(Failure failure) throws Exception {

    }

    @Override
    public void testAssumptionFailure(Failure failure) {

    }

    @Override
    public void testIgnored(Description description) throws Exception {

    }
}
