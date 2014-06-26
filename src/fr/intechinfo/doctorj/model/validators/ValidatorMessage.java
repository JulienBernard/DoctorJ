package fr.intechinfo.doctorj.model.validators;

import java.util.List;

/**
 * Created by Alexandre on 10/05/2014.
 */
public class ValidatorMessage {
    private boolean isValid;
    private List<ValidatorMessageElement> messageElements;

    public ValidatorMessage(boolean isValid, List<ValidatorMessageElement> messageElements) {
        this.isValid = isValid;
        this.messageElements = messageElements;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<ValidatorMessageElement> getMessage() {
        return messageElements;
    }
}
