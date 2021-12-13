package de.hsba.bi.fahrradkurrier.web;

import de.hsba.bi.fahrradkurrier.user.User;
import de.hsba.bi.fahrradkurrier.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class AnnotationAdvice {

    @Autowired
    UserService userService;

    @ModelAttribute("userRole")
    public String getCurrentUser() {
        User currentUser = userService.findCurrentUser();
        if (currentUser != null) {
            return currentUser.getRole().name();
        }
        return "";
    }

}
