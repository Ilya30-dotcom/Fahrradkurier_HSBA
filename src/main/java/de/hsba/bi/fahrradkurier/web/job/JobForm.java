package de.hsba.bi.fahrradkurier.web.job;

import de.hsba.bi.fahrradkurier.job.JobTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class JobForm {

    @NotNull()
    private JobTypeEnum type;

    private String ownToAddress;

    @NotBlank()
    private String toCity;

    @Pattern(regexp="^[^0-9]+$", message = "Darf nicht leer sein und darf keine Zahlen enthalten")
    private String toStreet;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben (z.B 123A)")
    private String toStreetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    private String toZip;

    private String ownFromAddress;

    @NotBlank()
    private String fromCity;

    @Pattern(regexp="^[^0-9]+$", message = "Darf nicht leer sein und darf keine Zahlen enthalten")
    private String fromStreet;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben (z.B 123A)")
    private String fromStreetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    private String fromZip;
}
