package de.hsba.bi.fahrradkurrier.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestUsers {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationStartedEvent.class)

    public void createTestUsers() {
        if (!userService.findAll().isEmpty()) {
            return;
        }
        userService.save(User.builder()
                .userName("Ilja")
                .firstName("Ilja")
                .lastName("Test")
                .birthday(LocalDate.now())
                .password("start")
                .role(UserRoleEnum.COURIER)
                .build());
        userService.save(User.builder()
                .userName("Arne")
                .firstName("Arne")
                .lastName("Test")
                .birthday(LocalDate.now())
                .password("start")
                .role(UserRoleEnum.CUSTOMER)
                .build());
    }
}
