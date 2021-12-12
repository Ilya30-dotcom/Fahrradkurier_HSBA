package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.job.JobTypeEnum;
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

    @NotBlank()
    private String toStreet;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben (z.B 123A)")
    private String toStreetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    private String toZip;

    private String ownFromAddress;

    @NotBlank()
    private String fromCity;

    @NotBlank()
    private String fromStreet;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben (z.B 123A)")
    private String fromStreetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    private String fromZip;
}
