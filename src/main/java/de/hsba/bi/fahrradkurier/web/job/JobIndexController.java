package de.hsba.bi.fahrradkurier.web.job;

import de.hsba.bi.fahrradkurier.job.JobEntity;
import de.hsba.bi.fahrradkurier.job.JobService;
import de.hsba.bi.fahrradkurier.user.User;
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
        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            model.addAttribute("job", jobService.findJobById(id));
        }
        return "job/jobDetails";
    }

    @GetMapping("/{jobId}/cancel")
    public String cancelJob(@PathVariable("jobId") Long id) {
        jobService.cancel(id);
        return "redirect:/jobs";
    }

    @GetMapping("/{jobId}/accept")
    public String acceptJob(@PathVariable("jobId") Long id) throws Exception {
        JobEntity job = jobService.findJobById(id);
        job.setCourier(userService.findCurrentUser());
        jobService.changeDetail(job);
        jobService.nextStatus(id);
        return "redirect:/jobs";
    }

    @GetMapping("/{jobId}/nextStatus")
    public String moveJobToNextStatus(@PathVariable("jobId") Long id) throws Exception {
        jobService.nextStatus(id);
        return "redirect:/jobs";
    }

}