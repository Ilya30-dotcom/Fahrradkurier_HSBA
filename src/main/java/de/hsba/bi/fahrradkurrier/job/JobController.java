package de.hsba.bi.fahrradkurrier.job;

import de.hsba.bi.fahrradkurrier.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobtest")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping(path = "/listAllJobsByStatusNew/", produces = "application/json")
    public List<JobEntity> listAllJobsByStatusNew() {
        return jobService.listAllJobsByStatusNew();
    }

    @GetMapping(path = "/findJobById/{jobId}", produces = "application/json")
    public JobEntity findJobById(@PathVariable("jobId") Long jobId) {
        return jobService.findJobById(jobId);
    }

    @GetMapping(path = "/findAllJobsByUserId/{userId}", produces = "application/json")
    public List<JobEntity> findAllJobsByUserId(@PathVariable Long userId) {
        return jobService.findAllJobsByUserId(userId);
    }

    @GetMapping(path = "/newjob")
    public void newJob(@RequestBody JobEntity job) {
        jobService.newJob(job);
    }

    @GetMapping(path = "/cancel/{jobId}")
    public void cancel(@PathVariable("jobId") Long jobId) {
        jobService.cancel(jobId);
    }

    @GetMapping(path = "/nextStatus/{jobId}", produces = "application/json")
    public JobStatusEnum nextStatus(@PathVariable("jobId") Long jobId) throws Exception {
        return jobService.nextStatus(jobId);
    }

    @GetMapping(path = "/changeDetail", produces = "application/json")
    public JobEntity changeDetail(@RequestBody JobEntity updatedJob) {
        try {
            return jobService.changeDetail(updatedJob);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}