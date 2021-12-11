package de.hsba.bi.fahrradkurrier.job;

import de.hsba.bi.fahrradkurrier.Common.AddressEntity;
import de.hsba.bi.fahrradkurrier.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobEntity {

    @Builder
    public JobEntity(User courier, User customer, JobTypeEnum type, LocalDateTime orderTimeStamp, AddressEntity deliveryAddress, AddressEntity pickUpAddress) {
        this.courier = courier;
        this.status = JobStatusEnum.NEW;
        this.customer = customer;
        this.type = type;
        this.orderTimeStamp = orderTimeStamp;
        this.deliveryAddress = deliveryAddress;
        this.pickUpAddress = pickUpAddress;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private User courier;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private JobStatusEnum status;

    @ManyToOne(optional = false)
    private User customer;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private JobTypeEnum type;

    @Basic(optional = false)
    private LocalDateTime orderTimeStamp;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private AddressEntity deliveryAddress;

    //TODO: CHECK IF MERGE IS OK
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private AddressEntity pickUpAddress;
}

//TODO: FETCH LAZY
