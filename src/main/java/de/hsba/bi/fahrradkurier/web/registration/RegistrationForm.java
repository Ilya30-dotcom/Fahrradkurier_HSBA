package de.hsba.bi.fahrradkurier.web.registration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class RegistrationForm {

    @Pattern(regexp="^[^0-9]+$", message = "Darf nicht leer sein und darf keine Zahlen enthalten")
    private String firstName;

    @NotBlank(message = "Bitte ergänzen Sie das Feld")
    private String userName;

    @Pattern(regexp="^[^0-9]+$", message = "Darf nicht leer sein und darf keine Zahlen enthalten")
    private String lastName;

    @NotNull(message = "Bitte ergänzen Sie das Feld")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "Bitte ergänzen Sie das Feld")
    private String password;

    @Pattern(regexp="^[^0-9]+$", message = "Darf nicht leer sein und darf keine Zahlen enthalten")
    private String street;

    @Pattern(regexp="^\\d{1,4}(?:[a-zA-z]{1,2})?$", message = "Korrekte Hausnummer angeben (z.B 123A)")
    private String streetNumber;

    @Size(min = 5, max = 5, message="Postleitzahl muss 5-stellig sein")
    private String zipCode;

    @Pattern(regexp="^[^0-9]+$", message = "Darf nicht leer sein und darf keine Zahlen enthalten")
    private String city;
}
