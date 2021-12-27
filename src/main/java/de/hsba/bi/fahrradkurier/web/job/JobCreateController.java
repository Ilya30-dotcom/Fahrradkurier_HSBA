package de.hsba.bi.fahrradkurier.web.job;

import de.hsba.bi.fahrradkurier.common.AddressEntity;
import de.hsba.bi.fahrradkurier.common.CityEnum;
import de.hsba.bi.fahrradkurier.job.JobEntity;
import de.hsba.bi.fahrradkurier.job.JobService;
import de.hsba.bi.fahrradkurier.user.User;
import de.hsba.bi.fahrradkurier.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/jobs/create")
@RequiredArgsConstructor
public class JobCreateController {

    private final JobFormConverter formConverter;
    private final JobService jobService;
    private final UserService userService;


    @ModelAttribute("userAddress")
    public AddressEntity getAddress() {
        User currentUser = userService.findCurrentUser();
        return currentUser.getAddress();
    }

    @GetMapping()
    public String index(Model model) {
        User currentUser = userService.findCurrentUser();
        CityEnum city = currentUser.getAddress().getCity();

        model.addAttribute("jobForm", formConverter.enrichCity(city));
        return "job/create";
    }

    @PostMapping()
    public String createJob(@ModelAttribute("jobForm") @Valid JobForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "job/create";
        }
        User currentUser = userService.findCurrentUser();
        JobEntity job = JobEntity.builder().customer(currentUser).build();
        jobService.newJob(formConverter.updateJob(job, form));
        redirectAttributes.addAttribute("jobId", job.getId()).addFlashAttribute("isNewJob", true);
        return "redirect:/jobs/{jobId}";
    }
}
