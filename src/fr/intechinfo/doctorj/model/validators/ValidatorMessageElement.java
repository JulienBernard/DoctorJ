package fr.intechinfo.doctorj.model.validators;

/**
 * Created by Alexandre on 26/06/2014.
 */
public class ValidatorMessageElement {
    private String element;
    ValidatorConstants code;

    public ValidatorMessageElement(String message, ValidatorConstants code) {
        this.element = message;
        this.code = code;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public ValidatorConstants getCode() {
        return code;
    }

    public void setCode(ValidatorConstants code) {
        this.code = code;
    }
}
