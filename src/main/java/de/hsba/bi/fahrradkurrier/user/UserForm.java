package de.hsba.bi.fahrradkurrier.user;

import de.hsba.bi.fahrradkurrier.Common.AddressEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class UserForm {

    @Pattern(regexp="^[^0-9]+$", message = "Zahlen hier invalide")
    @NotBlank(message = "bitte ergänzen Sie das Feld")
    private String firstName;

    @NotBlank(message = "bitte ergänzen Sie das Feld")
    private String userName;

    @Pattern(regexp="^[^0-9]+$", message = "Zahlen hier invalide")
    @NotBlank(message = "bitte ergänzen Sie das Feld")
    private String lastName;

    @NotNull(message = "bitte ergänzen Sie das Feld")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "bitte ergänzen Sie das Feld")
    private String password;

    @Pattern(regexp="^[^0-9]+$", message = "Zahlen hier invalide")
    @NotBlank(message = "bitte ergänzen Sie das Feld")
    private String street;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben (z.B 123A)")
    private String streetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    private String zipCode;

    @Pattern(regexp="^[^0-9]+$", message = "Zahlen hier invalide")
    @NotBlank (message = "bitte ergänzen Sie das Feld")
    private String city;
}
