package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.job.JobTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JobForm {

    @NotNull(message = "Bitte einen Versandtyp angeben")
    private JobTypeEnum type;

    private String currierName;

    private Long customerId;

}
