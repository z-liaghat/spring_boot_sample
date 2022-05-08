package com.example.springbootsample.springsecurity;

import com.example.springbootsample.springsecurity.model.User;
import com.example.springbootsample.springsecurity.model.UserDetailsRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

//Todo
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//        userValidator.validate(userForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }

//        userService.save(userForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
//
//        return "redirect:/fibonacci";
//    }


    @PostMapping("/registration")
    public String registration(@RequestBody User userForm, BindingResult bindingResult) {
        System.out.println("In register");
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration"+bindingResult.toString();
        }

        userService.save(userForm);

//        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
//        return "redirect:/fibonacci";
        return "successful";
    }



    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/fibonacci"})
    public String welcome(Model model) {
        return "welcome";
    }
}