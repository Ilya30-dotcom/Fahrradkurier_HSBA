package de.hsba.bi.fahrradkurier.web.registration;

import de.hsba.bi.fahrradkurier.user.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RegistrationFormConverter registrationFormConverter;


    @GetMapping()
    public String home(Model model) {
        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            return "redirect:/jobs";
        }
        model.addAttribute("userForm", new RegistrationForm());
        return "register";
    }

    @PostMapping()
    public String register(@ModelAttribute("userForm") @Valid RegistrationForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.save(registrationFormConverter.toEntity(userForm));
        redirectAttributes.addFlashAttribute("registeredSuccesfully", true);
        return "redirect:/login";
    }
}
