package de.hsba.bi.fahrradkurrier.web.register;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/registerNew")
public class RegisterController {

    @GetMapping
    public String indexRegister() {

        System.out.println("Trying to register");

            return "registerNew";

    }
    @PostMapping()
    public String register()
    {
        System.out.println("pls help me");
      //  repo.save(u);
        //session.setAttribute("message", "User registered successfully!");
        //return "redirect:/register";
        return "registerNew";
    }
}

