package de.hsba.bi.fahrradkurier.user;

import de.hsba.bi.fahrradkurier.Common.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFormConverter {
    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserForm userForm){
        AddressEntity mappedAddressEntity = AddressEntity.builder()
                .street(userForm.getStreet())
                .city(userForm.getCity())
                .streetNumber(userForm.getStreetNumber())
                .zipCode(userForm.getZipCode())
                .build();

        return User.builder()
                .userName(userForm.getUserName())
                .lastName(userForm.getLastName())
                .firstName(userForm.getFirstName())
                .birthday(userForm.getBirthday())
                .password(passwordEncoder.encode(userForm.getPassword())  )
                .role(UserRoleEnum.CUSTOMER)  // registration only possible for customers
                .address(mappedAddressEntity).build();


    }
}
