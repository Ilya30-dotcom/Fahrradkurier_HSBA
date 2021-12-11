package de.hsba.bi.fahrradkurrier.job;

public enum JobStatusEnum {
    NEW("New"),
    ACCEPTED("Accepted"),
    ON_THE_WAY("On The Way"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    public final String label;

    JobStatusEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
