package de.hsba.bi.fahrradkurier.job;


import de.hsba.bi.fahrradkurier.AbstractIntegrationTest;
import de.hsba.bi.fahrradkurier.common.AddressEntity;
import de.hsba.bi.fahrradkurier.common.CityEnum;
import de.hsba.bi.fahrradkurier.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JobServiceTest extends AbstractIntegrationTest {


    @Test
    void testListAllJobsByStatusNew() {

        User testUser = new User();
        AddressEntity testAddress = new AddressEntity();
        testAddress.setCity(CityEnum.HAMBURG);
        testUser.setAddress(testAddress);
        Mockito.doReturn(testUser).when(userService).findCurrentUser();

        List<JobEntity> resultList = jobService.listAllJobsByStatusNew();

        assertEquals(2, resultList.size(), "Job list should contain two jobs");

        resultList.forEach(job -> {
            assertEquals(JobStatusEnum.NEW, job.getStatus(), "Job list includes a job that is not new");
        });


        JobEntity jobWithChangedStatus = resultList.get(0);
        jobWithChangedStatus.setStatus(JobStatusEnum.ACCEPTED);
        jobRepository.save(jobWithChangedStatus);

        resultList = jobService.listAllJobsByStatusNew();

        assertEquals(1, resultList.size(), "Job list should contain one job after changing the status of the first job");

        resultList.forEach(job -> {
            assertEquals(JobStatusEnum.NEW, job.getStatus(), "Job list includes a job that is not new");
        });

    }



    @Test
    void testFindAllJobsByUserId() {

        List<JobEntity> resultList = jobService.findAllJobsByUserId(userId);

        assertEquals(1, resultList.size(), "Job list should contain one Jobs that is related to the userId");
        resultList.forEach( job -> {
            assertTrue(job.getCourier().getId().compareTo(userId) == 0 || job.getCustomer().getId().compareTo(userId) == 0,
                    "The job does not belong to the user");
        });
    }


    @Test
    void testNewJob() {
        long jobListSize = jobRepository.findAll().size();

        JobEntity testJob = JobEntity.builder()
                .customer(userRepository.getById(userId))
                .deliveryAddress(TEST_ADDRESS_1)
                .pickUpAddress(TEST_ADDRESS_2)
                .type(JobTypeEnum.PACKAGE)
                .build();

        JobEntity savedJob = jobService.newJob(testJob);


        assertEquals(jobListSize + 1L, jobRepository.findAll().size(), "Repository should now contain one job more");



        testJob.setStatus(JobStatusEnum.NEW);

        testJob.setOrderDate(TEST_DATE);

        testJob.setId(testJob.getId());

        assertTrue(compareJob(jobRepository.findById(savedJob.getId()).orElseThrow(), testJob),

                "Job from repository does not match saved job");

    }



    @Test

    void testNextStatus() throws Exception {

        long testJobId = getCurrentJobId();



        assertEquals(jobRepository.findById(testJobId).orElseThrow().getStatus(), JobStatusEnum.NEW);

        jobService.nextStatus(testJobId);



        assertEquals(jobRepository.findById(testJobId).orElseThrow().getStatus(), JobStatusEnum.ACCEPTED);

        jobService.nextStatus(testJobId);



        assertEquals(jobRepository.findById(testJobId).orElseThrow().getStatus(), JobStatusEnum.ON_THE_WAY);

        jobService.nextStatus(testJobId);



        assertEquals(jobRepository.findById(testJobId).orElseThrow().getStatus(), JobStatusEnum.DELIVERED);

        jobService.nextStatus(testJobId);



        JobEntity testJob = jobRepository.findById(testJobId).orElseThrow();

        testJob.setStatus(JobStatusEnum.CANCELLED);

        jobRepository.save(testJob);



        jobService.nextStatus(testJobId);



        assertEquals(jobRepository.findById(testJobId).orElseThrow().getStatus(), JobStatusEnum.CANCELLED, "Status status should stay cancelled");

    }



    @Test

    void testCancel() {

        long testJobId = getCurrentJobId();



        assertEquals(jobRepository.findById(testJobId).orElseThrow().getStatus(), JobStatusEnum.NEW);



        jobService.cancel(testJobId);

        assertEquals(jobRepository.findById(testJobId).orElseThrow().getStatus(), JobStatusEnum.CANCELLED);

    }



    @Test

    void testChangeDetail() throws Exception {

        long testJobId = getCurrentJobId();

        JobEntity testJob = jobRepository.findById(testJobId).orElseThrow();



        testJob.setType(JobTypeEnum.PACKAGE);

        testJob.setOrderDate(LocalDate.MAX);



        JobEntity changedTestJob = jobService.changeDetail(testJob);
        assertTrue(compareJob(testJob, changedTestJob));
        testJob.setType(JobTypeEnum.LETTER);
        testJob.setOrderDate(LocalDate.MIN);
        changedTestJob = jobService.changeDetail(testJob);

        assertTrue(compareJob(testJob, changedTestJob));
        assertTrue(compareJob(testJob, jobRepository.findById(testJobId).orElseThrow()));

    }



    private long getCurrentJobId() {

        return jobRepository.findAll().stream().findFirst().orElseThrow().getId();

    }



    private boolean compareJob(JobEntity job1, JobEntity job2) {

        return job1.getId().equals(job2.getId()) &&

                job1.getStatus().equals(job2.getStatus()) &&

                job1.getDeliveryAddress().equals(job2.getDeliveryAddress()) &&

                job1.getPickUpAddress().equals(job2.getPickUpAddress()) &&

                job1.getOrderDate().equals(job2.getOrderDate()) &&

                job1.getType().equals(job2.getType()) &&

                job1.getCustomer().getId().equals(job2.getCustomer().getId());

    }

}