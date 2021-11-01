package de.hsba.bi.fahrradkurrier.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class JobService {

    JobRepository jobRepository;

    public List<JobEntity> listAllJobsByStatusNew(Long userId) {
        return jobRepository.findAllByUserIdAndStatus(userId, JobStatusEnum.NEW);
    }

    public JobEntity findJobById(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow();
    }

    public List<JobEntity> findAllJobsByUserId(Long userId) {
        return jobRepository.findAllByCustomerIdOrCourierId(userId, userId);
    }

    public void newJob(JobEntity job) {
        jobRepository.save(job);
    }

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
                newStatus = JobStatusEnum.DELIVERED;
                break;
            case DELIVERED:
            case CANCELLED:
                newStatus = JobStatusEnum.CANCELLED;
                break;
        }
        if (newStatus == null) {
            throw new Exception("Couldn't find Status");
        }
        else{
            jobRepository.updateStatus(jobId, newStatus);
            return newStatus;
        }
    }

    void cancel(Long jobId) {
        jobRepository.updateStatus(jobId, JobStatusEnum.CANCELLED);
    }

    //TODO: changeDetail()


}
