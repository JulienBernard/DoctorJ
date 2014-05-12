package fr.intechinfo.doctorj.model;

/**
 * Created by Alexandre on 10/05/2014.
 */
public class ValidatorMessage {
    private boolean isValid;
    private String message;

    public ValidatorMessage(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }
}
