package de.hsba.bi.fahrradkurier.common;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AddressEntity {

    @Builder
    public AddressEntity(String street, String streetNumber, String zipCode, CityEnum city) {
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
    private CityEnum city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEntity)) return false;
        AddressEntity address = (AddressEntity) o;
        return Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getStreetNumber(), address.getStreetNumber()) && Objects.equals(getZipCode(), address.getZipCode()) && Objects.equals(getCity(), address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getStreetNumber(), getZipCode(), getCity());
    }

}
