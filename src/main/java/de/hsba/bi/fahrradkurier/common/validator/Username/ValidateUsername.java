package de.hsba.bi.fahrradkurier.common.validator.Username;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateUsername {
    String message() default "Benutzername existiert bereits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
