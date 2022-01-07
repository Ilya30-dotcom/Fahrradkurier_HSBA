package de.hsba.bi.fahrradkurier.job;

import de.hsba.bi.fahrradkurier.common.AddressEntity;
import de.hsba.bi.fahrradkurier.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobEntity {

    @Builder
    public JobEntity(User courier, User customer, JobTypeEnum type, LocalDate orderDate, AddressEntity deliveryAddress, AddressEntity pickUpAddress) {
        this.courier = courier;
        this.status = JobStatusEnum.NEW;
        this.customer = customer;
        this.type = type;
        this.orderDate = orderDate;
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
    private LocalDate orderDate;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private AddressEntity deliveryAddress;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private AddressEntity pickUpAddress;
}