package de.hsba.bi.fahrradkurier;

import de.hsba.bi.fahrradkurier.common.AddressEntity;
import de.hsba.bi.fahrradkurier.common.AddressRepository;
import de.hsba.bi.fahrradkurier.common.CityEnum;
import de.hsba.bi.fahrradkurier.common.TestData;
import de.hsba.bi.fahrradkurier.job.JobEntity;
import de.hsba.bi.fahrradkurier.job.JobRepository;
import de.hsba.bi.fahrradkurier.job.JobService;
import de.hsba.bi.fahrradkurier.job.JobTypeEnum;
import de.hsba.bi.fahrradkurier.user.User;
import de.hsba.bi.fahrradkurier.user.UserRepository;
import de.hsba.bi.fahrradkurier.user.UserRoleEnum;
import de.hsba.bi.fahrradkurier.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public abstract class AbstractIntegrationTest {


    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JobService jobService;


    @SpyBean
    public UserService userService;

    @Autowired
    public UserRepository userRepository;


    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    public JobRepository jobRepository;

    //Mocking the TestData class in order to prevent loading the "normal" test data.

    //This achieves an independent test execution

    @MockBean
    private TestData testData;



    @MockBean
    public Clock clock;



    private final String PASSWORD = "start";



    public final AddressEntity TEST_ADDRESS_1 = AddressEntity.builder()

            .city(CityEnum.HAMBURG)

            .street("Jungfernstieg")

            .zipCode("22022")

            .streetNumber("1").build();



    public final AddressEntity TEST_ADDRESS_2 = AddressEntity.builder()

            .city(CityEnum.HAMBURG)

            .street("Jungfernstieg")

            .zipCode("22022")

            .streetNumber("2").build();



    public final LocalDate TEST_DATE = LocalDate.of(2000, 1, 1);

    public Long userId = null;

    @BeforeEach

    void initTestData() {

        userRepository.deleteAll();

        jobRepository.deleteAll();

        addressRepository.deleteAll();

        Clock fixedClock = Clock.fixed(TEST_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());



        Mockito.when(clock.instant()).thenReturn(fixedClock.instant());

        Mockito.when(clock.getZone()).thenReturn(fixedClock.getZone());



        User user1 = User.builder()

                .userName("Ilja")

                .firstName("Ilja")

                .lastName("Test")

                .birthday(LocalDate.now())

                .password(passwordEncoder.encode(PASSWORD))

                .role(UserRoleEnum.COURIER)

                .address(TEST_ADDRESS_1)

                .build();



        User user2 = User.builder()

                .userName("Arne")

                .firstName("Arne")

                .lastName("Test")

                .birthday(LocalDate.now())

                .password(passwordEncoder.encode(PASSWORD))

                .role(UserRoleEnum.CUSTOMER)

                .address(TEST_ADDRESS_2)

                .build();

        user1 = userService.save(user1);

        user2 = userService.save(user2);



        userId = user1.getId();



        //TODO: CHECK IF USER IS REALLY CURRIER

        jobService.newJob(

                JobEntity.builder()

                        .courier(user1)

                        .customer(user2)

                        .deliveryAddress(TEST_ADDRESS_1)

                        .pickUpAddress(TEST_ADDRESS_2)

                        .type(JobTypeEnum.LETTER)

                        .build()

        );



        jobService.newJob(

                JobEntity.builder()

                        .customer(user2)

                        .deliveryAddress(TEST_ADDRESS_2)

                        .pickUpAddress(TEST_ADDRESS_1)

                        .type(JobTypeEnum.PACKAGE)

                        .build()

        );



    }

}