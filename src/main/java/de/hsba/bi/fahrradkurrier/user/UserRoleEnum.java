package de.hsba.bi.fahrradkurrier.user;

public enum UserRoleEnum {
    COURIER("Kurier"),
    CUSTOMER("Kunde");

    public final String label;

    UserRoleEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
