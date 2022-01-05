package de.hsba.bi.fahrradkurier.user;

import de.hsba.bi.fahrradkurier.AbstractIntegrationTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



import java.time.LocalDate;

import java.util.List;



public class UserServiceTest extends AbstractIntegrationTest {



    @Test

    void testFindCouriers() {

        List<User> couriers =  userService.findCouriers();

        assertEquals(couriers.size(), 1L);

        assertEquals(couriers.get(0).getRole(), UserRoleEnum.COURIER);

    }



    @Test

    void testFindCustomers() {

        List<User> customers =  userService.findCustomers();

        assertEquals(customers.size(), 1L);

        assertEquals(customers.get(0).getRole(), UserRoleEnum.CUSTOMER);



    }



    @Test

    void testSave() {

        User testUser = User.builder()

                .address(TEST_ADDRESS_1)

                .userName("UserName")

                .firstName("FirstName")

                .lastName("LastName")

                .birthday(LocalDate.of(2020, 12, 6))

                .role(UserRoleEnum.COURIER)

                .password(passwordEncoder.encode("PW"))

                .build();



        User savedUser = userService.save(testUser);

        assertEquals(testUser, savedUser, "User returned by save method is not the test user");



        User thisUser = userRepository.findById(savedUser.getId()).orElseThrow();

        assertTrue(compareUser(testUser, userRepository.findById(savedUser.getId()).orElseThrow()),

                "User saved in database is not the test user");

    }



    private boolean compareUser(User user1, User user2) {

        return user1.getId().equals(user2.getId()) &&

                user1.getUserName().equals(user2.getUserName()) &&

                user1.getFirstName().equals(user2.getFirstName()) &&

                user1.getLastName().equals(user2.getLastName()) &&

                user1.getBirthday().isEqual(user2.getBirthday()) &&

                user1.getRole().equals(user2.getRole()) &&

                user1.getAddress().equals(user2.getAddress()) &&

                user1.getPassword().equals(user2.getPassword());

    }

}
