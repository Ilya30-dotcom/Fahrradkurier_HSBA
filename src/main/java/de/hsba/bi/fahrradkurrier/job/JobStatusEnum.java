package de.hsba.bi.fahrradkurrier.job;

public enum JobStatusEnum {
    NEW("Neu"),
    ACCEPTED("Angenommen"),
    ON_THE_WAY("Auf dem Weg"),
    DELIVERED("Geliefert"),
    CANCELLED("Storniert");

    public final String label;

    JobStatusEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
