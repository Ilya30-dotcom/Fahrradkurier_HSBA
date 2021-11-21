package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.job.JobEntity;
import de.hsba.bi.fahrradkurrier.job.JobService;
import de.hsba.bi.fahrradkurrier.job.JobTypeEnum;
import de.hsba.bi.fahrradkurrier.user.User;
import de.hsba.bi.fahrradkurrier.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/jobs/create")
@RequiredArgsConstructor
public class JobCreateController {

    private final JobFormConverter formConverter;
    private final JobService jobService;
    private final UserService userService;

    @ModelAttribute("jobTypeEnums")
    public Map<String, JobTypeEnum> getTypes() {
        Map<String, JobTypeEnum> types = Map.of(
                "Brief", JobTypeEnum.LETTER,
                "Paket", JobTypeEnum.PACKAGE
        );
        return types;
    }

    @GetMapping()
    public String index(Model model) {
        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
            JobEntity job = JobEntity.builder().pickUpAddress(currentUser.getAddress()).build();
            model.addAttribute("jobForm", formConverter.enrichAddress(job));
            return "job/create";
        }
        return "redirect:/jobs";
    }

    @PostMapping()
    public String createJob(@ModelAttribute("jobForm") @Valid JobForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "job/create";
        }
        User currentUser = userService.findCurrentUser();
        JobEntity job = JobEntity.builder().customer(currentUser).build();
        jobService.newJob(formConverter.updateJob(job, form));
        return "redirect:/jobs";
    }
}
