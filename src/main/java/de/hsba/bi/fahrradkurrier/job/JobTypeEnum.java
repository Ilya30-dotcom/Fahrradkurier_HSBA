package de.hsba.bi.fahrradkurrier.job;

public enum JobTypeEnum {
    LETTER("Brief"),
    PACKAGE("Paket");

    public final String label;

    JobTypeEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
