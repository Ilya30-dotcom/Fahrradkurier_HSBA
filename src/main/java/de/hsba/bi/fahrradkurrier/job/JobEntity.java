package de.hsba.bi.fahrradkurrier.job;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobEntity {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = true)
    private Long courierId;

    @Basic(optional = false)
    private JobStatusEnum status = JobStatusEnum.NEW;

    @Basic(optional = false)
    private Long customerId;

    @Basic(optional = false)
    private JobTypeEnum type;

    @Basic(optional = false)
    private LocalDate orderDate;
}
