package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.job.JobEntity;
import de.hsba.bi.fahrradkurrier.job.JobService;
import de.hsba.bi.fahrradkurrier.user.User;
import de.hsba.bi.fahrradkurrier.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs/create")
@RequiredArgsConstructor
public class JobCreateController {

    private final UserService userService;
    private final JobService jobService;


    @GetMapping()
    public String index(Model model) {
        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
            return "job/create";
        }
        return "redirect:/jobs";
    }

    @PostMapping("/")
    public String createJob(@RequestBody JobEntity job) {
        JobEntity.builder().build();
        jobService.newJob(job);
        return "redirect:/jobs";
    }
}
