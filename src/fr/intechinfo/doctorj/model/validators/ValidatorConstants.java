package fr.intechinfo.doctorj.model.validators;

/**
 * Created by Alexandre on 26/06/2014.
 */
public enum ValidatorConstants {
    UNKNOWN(""),
    OK("-fx-fill: green; -fx-font-weight:bold;"),
    WARNING("-fx-fill: orange; -fx-font-weight:bold;"),
    ERROR("-fx-fill: red; -fx-font-weight:bold;"),
    BLANK(""),
    INFO("-fx-fill: #3b82c9; -fx-font-weight:bold;"),
    TITLE("-fx-font-size: 14px; -fx-font-weight:bold;"),
    SUBTITLE("-fx-font-weight:bold;");

    private String style;

    private ValidatorConstants(String style) {
        this.style = style;
    }

    public String getStyle () {
        return style;
    }
}
