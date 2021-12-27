package de.hsba.bi.fahrradkurier.web.job;

import de.hsba.bi.fahrradkurier.job.JobEntity;
import de.hsba.bi.fahrradkurier.job.JobService;
import de.hsba.bi.fahrradkurier.job.JobStatusEnum;
import de.hsba.bi.fahrradkurier.user.User;
import de.hsba.bi.fahrradkurier.user.UserRoleEnum;
import de.hsba.bi.fahrradkurier.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobIndexController {

    private final UserService userService;
    private final JobService jobService;


    @GetMapping()
    public String index(Model model) {
        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            model.addAttribute("userJobs", jobService.findAllJobsByUserId(currentUser.getId()));
            model.addAttribute("newJobs", jobService.listAllJobsByStatusNew());
        }
        return "job/index";
    }

    @GetMapping("/{jobId}")
    public String showJob(@PathVariable("jobId") Long id, Model model) {
        JobEntity jobEntity = jobService.findJobById(id);
        if (jobEntity.getStatus().equals(JobStatusEnum.NEW)) {
            if (userService.findCurrentUser().getRole().equals(UserRoleEnum.CUSTOMER)) {
                userService.checkIfUserAllowed(jobEntity, false, true);
            }
        } else {
            userService.checkIfUserAllowed(jobEntity, true, true);
        }


        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            model.addAttribute("job", jobService.findJobById(id));
        }
        return "job/jobDetails";
    }

    @GetMapping("/{jobId}/cancel")
    public String cancelJob(@PathVariable("jobId") Long id) {
        userService.checkIfUserAllowed(jobService.findJobById(id), false, true);

        jobService.cancel(id);
        return "redirect:/jobs";
    }

    @GetMapping("/{jobId}/accept")
    public String acceptJob(@PathVariable("jobId") Long id) throws Exception {
        String userRole = userService.findCurrentUser().getRole().name();
        if (userRole == "COURIER") {
            JobEntity job = jobService.findJobById(id);
            job.setCourier(userService.findCurrentUser());
            jobService.changeDetail(job);
            jobService.nextStatus(id);
        }
        return "redirect:/jobs";
    }

    @GetMapping("/{jobId}/nextStatus")
    public String moveJobToNextStatus(@PathVariable("jobId") Long id) throws Exception {
        userService.checkIfUserAllowed(jobService.findJobById(id), true, false);

        jobService.nextStatus(id);
        return "redirect:/jobs";
    }
}