package de.hsba.bi.fahrradkurier.common;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AddressEntity {

    @Builder
    public AddressEntity(String street, String streetNumber, String zipCode, String city) {
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
    private String streetNumber;

    @Basic(optional = false)
    private String zipCode;

    @Basic(optional = false)
    private String city;
}
