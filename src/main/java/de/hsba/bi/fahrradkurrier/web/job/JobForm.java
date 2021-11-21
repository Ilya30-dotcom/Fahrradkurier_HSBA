package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.job.JobTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JobForm {

    @NotNull(message = "Bitte eine Lieferart auswählen")
    private JobTypeEnum type;

    @NotBlank(message = "Bitte ausfüllen")
    private String toCity;

    @NotBlank(message = "Bitte ausfüllen")
    private String toStreet;

    @NotNull(message = "Bitte ausfüllen")
    private Integer toStreetNumber;

    @NotBlank(message = "Bitte ausfüllen")
    private String toZip;

    @NotBlank(message = "Bitte ausfüllen")
    private String fromCity;

    @NotBlank(message = "Bitte ausfüllen")
    private String fromStreet;

    @NotNull(message = "Bitte ausfüllen")
    private Integer fromStreetNumber;

    @NotBlank(message = "Bitte ausfüllen")
    private String fromZip;
}
