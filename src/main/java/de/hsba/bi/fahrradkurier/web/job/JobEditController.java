package de.hsba.bi.fahrradkurier.web.job;

import de.hsba.bi.fahrradkurier.job.JobEntity;
import de.hsba.bi.fahrradkurier.job.JobService;
import de.hsba.bi.fahrradkurier.user.UserService;
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


    @ModelAttribute("job")
    public JobEntity getJob(@PathVariable("jobId") Long id) {
        userService.checkIfUserAllowed(jobService.findJobById(id), false, true);

        return jobService.findJobById(id);
    }

    @GetMapping()
    public String showEditableJob(@PathVariable("jobId") Long id, Model model) {
        userService.checkIfUserAllowed(jobService.findJobById(id), false, true);
        JobEntity job = jobService.findJobById(id);
        model.addAttribute("jobForm", formConverter.convertToJobForm(job));

        return "job/jobEdit";
    }

    @PostMapping()
    public String updateJob(@PathVariable("jobId") Long id, @ModelAttribute("jobForm") @Valid JobForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {
        userService.checkIfUserAllowed(jobService.findJobById(id), false, true);

        if (bindingResult.hasErrors()) {
            return "job/jobEdit";
        }
        JobEntity job = jobService.findJobById(id);
        jobService.changeDetail(formConverter.updateJob(job, form));
        redirectAttributes.addAttribute("jobId", job.getId()).addFlashAttribute("jobEditedSuccessfully", true);;
        return "redirect:/jobs/{jobId}";
    }
}
