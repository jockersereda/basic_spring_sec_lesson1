package ua.com.owu.basic_spring_sec_lesson1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.basic_spring_sec_lesson1.models.User;
import ua.com.owu.basic_spring_sec_lesson1.services.user.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private UserService userService;

    @GetMapping("/")
    public String home(){
       return "hello";
    }

    private PasswordEncoder passwordEncoder;
    @PostMapping("/saveUser")
    public void save(@RequestBody User user){

        /*String password = user.getPassword();
        String encode= passwordEncoder.encode(password);  // - це те що знаходиться під капотом в методі нище
        user.setPassword(encode);*/

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
}
