package de.hsba.bi.fahrradkurrier.Common;

import de.hsba.bi.fahrradkurrier.job.JobEntity;
import de.hsba.bi.fahrradkurrier.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AddressEntity {

    @Builder
    public AddressEntity(String street, Integer streetNumber, String zipCode, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String street;

    @Basic(optional = false)
    private Integer streetNumber;

    @Basic(optional = false)
    private String zipCode;

    @Basic(optional = false)
    private String city;
}
