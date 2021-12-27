package de.hsba.bi.fahrradkurier.common;

public enum CityEnum{


    BERLIN("Berlin"),
    HAMBURG("Hamburg"),
    FRANKFURT("Frankfurt"),
    MUNICH("München");


    public final String label;

    CityEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static CityEnum fromString(String text) {
        for (CityEnum i : CityEnum.values()) {
            if (i.label.equalsIgnoreCase(text)) {
                return i;
            }
        }
        return null;
    }
}
