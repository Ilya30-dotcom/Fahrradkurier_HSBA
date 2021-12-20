package de.hsba.bi.fahrradkurier.common;

public enum CityEnum{


    BERLIN("Berlin"),
    HAMBURG("Hamburg"),
    FRANKFURT("Frankfurt"),
    MÜNCHEN("München");


    public final String label;

    CityEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
