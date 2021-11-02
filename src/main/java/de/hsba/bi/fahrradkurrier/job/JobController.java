package de.hsba.bi.fahrradkurrier.job;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping(path = "/listAllJobsByStatusNew/{userId}", produces = "application/json")
    public List<JobEntity> listAllJobsByStatusNew(@PathVariable("userId") Long userId) {
        return jobService.listAllJobsByStatusNew(userId);
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
}