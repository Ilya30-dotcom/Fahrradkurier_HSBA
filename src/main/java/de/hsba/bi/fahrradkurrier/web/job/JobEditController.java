package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.job.JobEntity;
import de.hsba.bi.fahrradkurrier.job.JobService;
import de.hsba.bi.fahrradkurrier.user.User;
import de.hsba.bi.fahrradkurrier.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/jobs/{jobId}/edit")
@RequiredArgsConstructor
public class JobEditController {

    private final JobFormConverter formConverter;
    private final JobService jobService;
    private final UserService userService;


    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        User currentUser = userService.findCurrentUser();
        return currentUser;
    }

    @ModelAttribute("job")
    public JobEntity getJob(@PathVariable("jobId") Long id) {
        JobEntity job = jobService.findJobById(id);
        return job;
    }

    @GetMapping()
    public String showEditableJob(@PathVariable("jobId") Long id, Model model) {
        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            JobEntity job = jobService.findJobById(id);
            model.addAttribute("jobForm", formConverter.convertToJobForm(job));
        }
        return "job/jobEdit";
    }

    @PostMapping()
    public String updateJob(@PathVariable("jobId") Long id, @ModelAttribute("jobForm") @Valid JobForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return "job/jobEdit";
        }
        JobEntity job = jobService.findJobById(id);
        jobService.changeDetail(formConverter.updateJob(job, form));
        redirectAttributes.addAttribute("jobId", job.getId()).addFlashAttribute("jobEditedSuccessfully", true);;
        return "redirect:/jobs/{jobId}";
    }
}
