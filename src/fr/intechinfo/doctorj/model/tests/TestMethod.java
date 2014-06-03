package fr.intechinfo.doctorj.model.tests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Alexandre on 28/05/2014.
 */
public class TestMethod {
    private Object object;
    private Class<?>[] listTypes;
    private Object[] parameters;
    private String name;

    public TestMethod(Object o, String n) {
        object = o;
        name = n;
    }

    public TestMethod withParameterTypes(Class<?>... t) {
        listTypes = t;
        return this;
    }

    public TestMethod withParameters(Object... p) throws InvocationTargetException, IllegalAccessException {
        parameters = p;
        return this;
    }

    public Object execute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(listTypes == null) {
            return object.getClass().getMethod(name).invoke(object);
        }
        else {
            return object.getClass().getMethod(name, listTypes).invoke(object, parameters);
        }
    }
}
