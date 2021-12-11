package de.hsba.bi.fahrradkurrier.Common;

import de.hsba.bi.fahrradkurrier.job.JobEntity;
import de.hsba.bi.fahrradkurrier.job.JobService;
import de.hsba.bi.fahrradkurrier.job.JobTypeEnum;
import de.hsba.bi.fahrradkurrier.user.User;
import de.hsba.bi.fahrradkurrier.user.UserRoleEnum;
import de.hsba.bi.fahrradkurrier.user.UserService;
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
                .city("Hamburg")
                .street("Jungfernstieg")
                .zipCode("22022")
                .streetNumber("1").build();

        AddressEntity address2 = AddressEntity.builder()
                .city("Hamburg")
                .street("Jungfernstieg")
                .zipCode("22022")
                .streetNumber("2").build();
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
                .role(UserRoleEnum.CUSTOMER)
                .address(address2)
                .build();
        userService.save(user1);
        userService.save(user2);


        //TODO: CHECK IF USER IS REALLY CURRIER
        jobService.newJob(
                JobEntity.builder()
                        .courier(user1)
                        .customer(user2)
                        .deliveryAddress(address1)
                        .pickUpAddress(address2)
                        .type(JobTypeEnum.LETTER)
                        .build()
        );

        jobService.newJob(
                JobEntity.builder()
                        .courier(user1)
                        .customer(user2)
                        .deliveryAddress(address2)
                        .pickUpAddress(address1)
                        .type(JobTypeEnum.PACKAGE)
                        .build()
        );
    }


}
