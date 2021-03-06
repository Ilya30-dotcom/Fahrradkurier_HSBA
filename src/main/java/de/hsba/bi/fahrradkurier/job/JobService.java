package de.hsba.bi.fahrradkurier.job;

import de.hsba.bi.fahrradkurier.common.AddressRepository;
import de.hsba.bi.fahrradkurier.user.User;
import de.hsba.bi.fahrradkurier.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Class that takes care of everything related to the JobEntity
 */
@RequiredArgsConstructor
@Service
@Transactional
public class JobService {

    @Autowired
    private final Clock clock;
    private final JobRepository jobRepository;
    private final AddressRepository addressRepository;
    private final UserService userService;

    /**
     * Find all new jobs that are located in the users residental city
     *
     * @return List of all JobEnitities found in the database
     */
    public List<JobEntity> listAllJobsByStatusNew() {
        User currentUser = userService.findCurrentUser();
        return jobRepository.findAllByStatusAndCityIsResidentOrderByOrderDateDesc(JobStatusEnum.NEW, currentUser.getAddress().getCity());
    }

    /**
     * Get a job by JobId
     *
     * @param jobId
     * @return JobEntity of the job that corresponds to the JobId
     */
    public JobEntity findJobById(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow();
    }

    /**
     * Finds all jobs that are associated with a specific user
     *
     * @param userId Id of the user of interest
     * @return List containing every JobEntity associated with the userId
     */
    public List<JobEntity> findAllJobsByUserId(Long userId) {
        return jobRepository.findAllByCustomerIdOrCourierIdOrderByOrderDateDesc(userId, userId);
    }

    /**
     * Creates a new job
     *
     * @param job New job that should be saved
     */
    public JobEntity newJob(JobEntity job) {
        job.setOrderDate(LocalDateTime.now(clock));
        //make sure no existing job can be changed
        job.setId(null);
        addressRepository.saveAll(Set.of(job.getDeliveryAddress(),job.getPickUpAddress()));
        return jobRepository.save(job);
    }

    /**
     * Change the status of a specific job to the next one.
     * If the status is 'CANCELLED'  or 'DELIVERED' the status will stay the same
     *
     * @param jobId The JobId of that job thats status should get updated
     * @return The new status of the job
     * @throws Exception Throws an exception if the jobStatus is null
     */
    public JobStatusEnum nextStatus(Long jobId) throws Exception {
        JobEntity job = findJobById(jobId);
        JobStatusEnum newStatus = null;
        switch (job.getStatus()) {

            case NEW:
                newStatus = JobStatusEnum.ACCEPTED;
                break;
            case ACCEPTED:
                newStatus = JobStatusEnum.ON_THE_WAY;
                break;
            case ON_THE_WAY:
            case DELIVERED:
                newStatus = JobStatusEnum.DELIVERED;
                break;
            case CANCELLED:
                newStatus = JobStatusEnum.CANCELLED;
                break;
        }
        if (newStatus == null) {
            throw new Exception("Status is null");
        } else {
            jobRepository.updateStatus(jobId, newStatus);
            return newStatus;
        }
    }

    /**
     * Set the status of a specific job to 'CANCELLED'
     *
     * @param jobId The JobId of the Job that should be cancelled
     */
    public void cancel(Long jobId) {
        jobRepository.updateStatus(jobId, JobStatusEnum.CANCELLED);
    }

    /**
     * Updates an existing job
     *
     * @param updatedJob JobEntity Object including the jobId of the Job
     *                  that should get updated
     * @return Updated JobEntity Object
     * @throws Exception Throws exception if the JobId does not exist
     */
    public JobEntity changeDetail(JobEntity updatedJob) throws Exception {
        Optional<JobEntity> currentJob = jobRepository.findById(updatedJob.getId());

        if(currentJob.isPresent()) {
            //make sure user can't change orderDate
            updatedJob.setOrderDate(currentJob.get().getOrderDate());
            return jobRepository.save(updatedJob);
        } else {
            throw new Exception("Job not found");
        }
    }
}
