package com.example.springbootsample.auth;

import com.example.springbootsample.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody User user){
//        userValidator.validate(user, bindingResult);

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("confirm password mismatch");
        }

        userService.save(user);

//        securityService.autoLogin(user.getUsername(), user.getPassword());

        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @GetMapping({"/", "/fibonacci"})
    public String welcome(Model model) {
        return "welcome";
    }
}