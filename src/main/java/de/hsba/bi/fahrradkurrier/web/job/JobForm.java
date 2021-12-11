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

    @NotNull(message = "Bitte eine Lieferart auswählen")
    private JobTypeEnum type;

    private String ownToAddress;

    @NotBlank(message = "Bitte ausfüllen")
    private String toCity;

    @NotBlank(message = "Bitte ausfüllen")
    private String toStreet;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben")
    @NotNull(message = "Bitte ausfüllen")
    private String toStreetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    @NotBlank(message = "Bitte ausfüllen")
    private String toZip;

    private String ownFromAddress;

    @NotBlank(message = "Bitte ausfüllen")
    private String fromCity;

    @NotBlank(message = "Bitte ausfüllen")
    private String fromStreet;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben")
    @NotNull(message = "Bitte ausfüllen")
    private String fromStreetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    @NotBlank(message = "Bitte ausfüllen")
    private String fromZip;
}
