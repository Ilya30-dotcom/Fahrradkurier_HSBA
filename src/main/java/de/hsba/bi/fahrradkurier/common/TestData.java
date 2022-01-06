package de.hsba.bi.fahrradkurier.common;

import de.hsba.bi.fahrradkurier.job.JobEntity;
import de.hsba.bi.fahrradkurier.job.JobService;
import de.hsba.bi.fahrradkurier.job.JobTypeEnum;
import de.hsba.bi.fahrradkurier.user.User;
import de.hsba.bi.fahrradkurier.user.UserRoleEnum;
import de.hsba.bi.fahrradkurier.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestData {

    private final UserService userService;
    private final JobService jobService;
    private final PasswordEncoder passwordEncoder;
    private final String PASSWORD = "start";

    @EventListener(ApplicationStartedEvent.class)
    public void createTestUsers() {
        if (!userService.findAll().isEmpty()) {
            return;
        }

        AddressEntity address1 = AddressEntity.builder()
                .city(CityEnum.HAMBURG)
                .street("Jungfernstieg")
                .zipCode("22022")
                .streetNumber("1").build();

        AddressEntity address2 = AddressEntity.builder()
                .city(CityEnum.HAMBURG)
                .street("Jungfernstieg")
                .zipCode("22022")
                .streetNumber("2").build();

        AddressEntity address3 = AddressEntity.builder()
                .city(CityEnum.MUNICH)
                .street("Maximilian Straße")
                .zipCode("22022")
                .streetNumber("5").build();

        AddressEntity address4 = AddressEntity.builder()
                .city(CityEnum.MUNICH)
                .street("Maximilian Straße")
                .zipCode("22022")
                .streetNumber("2").build();

        AddressEntity address5 = AddressEntity.builder()
                .city(CityEnum.FRANKFURT)
                .street("Neue Mainzer Straße")
                .zipCode("60311")
                .streetNumber("52").build();

        AddressEntity address6 = AddressEntity.builder()
                .city(CityEnum.FRANKFURT)
                .street("Neue Mainzer Straße")
                .zipCode("60311")
                .streetNumber("59").build();

        AddressEntity address7 = AddressEntity.builder()
                .city(CityEnum.BERLIN)
                .street("Unter den Linden")
                .zipCode("10117")
                .streetNumber("78").build();

        AddressEntity address8 = AddressEntity.builder()
                .city(CityEnum.BERLIN)
                .street("Unter den Linden")
                .zipCode("10117")
                .streetNumber("79").build();


        User user1 = User.builder()
                .userName("Ilja")
                .firstName("Ilja")
                .lastName("Test")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.COURIER)
                .address(address1)
                .build();

        User user2 = User.builder()
                .userName("Arne")
                .firstName("Arne")
                .lastName("Test")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.COURIER)
                .address(address3)
                .build();

        User user3 = User.builder()
                .userName("Max")
                .firstName("Max")
                .lastName("Test")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.COURIER)
                .address(address5)
                .build();

        User user4 = User.builder()
                .userName("Abdullah")
                .firstName("Abdullah")
                .lastName("Test")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.COURIER)
                .address(address7)
                .build();

        User user5 = User.builder()
                .userName("KundeHamburg")
                .firstName("Kunde")
                .lastName("Hamburg")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.CUSTOMER)
                .address(address2)
                .build();

        User user6 = User.builder()
                .userName("KundeMuenchen")
                .firstName("Kunde")
                .lastName("Muenchen")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.CUSTOMER)
                .address(address4)
                .build();

        User user7 = User.builder()
                .userName("KundeFrankfurt")
                .firstName("Kunde")
                .lastName("Frankfurt")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.CUSTOMER)
                .address(address6)
                .build();

        User user8 = User.builder()
                .userName("KundeBerlin")
                .firstName("Kunde")
                .lastName("Berlin")
                .birthday(LocalDate.now())
                .password(passwordEncoder.encode(PASSWORD))
                .role(UserRoleEnum.CUSTOMER)
                .address(address8)
                .build();

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);


        //TODO: CHECK IF USER IS REALLY CURRIER
        jobService.newJob(
                JobEntity.builder()
                        .courier(null)
                        .customer(user5)
                        .deliveryAddress(address1)
                        .pickUpAddress(address2)
                        .type(JobTypeEnum.LETTER)
                        .build()
        );

        jobService.newJob(
                JobEntity.builder()
                        .courier(null)
                        .customer(user6)
                        .deliveryAddress(address3)
                        .pickUpAddress(address4)
                        .type(JobTypeEnum.PACKAGE)
                        .build()
        );

        jobService.newJob(
                JobEntity.builder()
                        .courier(null)
                        .customer(user7)
                        .deliveryAddress(address5)
                        .pickUpAddress(address6)
                        .type(JobTypeEnum.PACKAGE)
                        .build()
        );

        jobService.newJob(
                JobEntity.builder()
                        .courier(null)
                        .customer(user8)
                        .deliveryAddress(address7)
                        .pickUpAddress(address8)
                        .type(JobTypeEnum.PACKAGE)
                        .build()
        );
    }


}
