package com.alupit.ciit.passkeeper.controller;

import com.alupit.ciit.passkeeper.entity.UserInfo;
import com.alupit.ciit.passkeeper.service.CacheService;
import com.alupit.ciit.passkeeper.service.HashingService;
import com.alupit.ciit.passkeeper.service.AccountValidationService;
import com.alupit.ciit.passkeeper.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private HashingService hashingService;

    @Autowired
    private AccountValidationService accountValidationService;

    // Handler method for GET requests to "/register" endpoint
    @GetMapping("/register")
    public String showRegistrationForm(HttpServletResponse response) {

        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);

        // Return the name of the view (register.jsp in this case)
        return "register";
    }

    @PostMapping("/register")
    public String register(String username, String password, String confirmpassword) throws Exception {
        // Validate user credentials using PasswordService
        if (password.equals(confirmpassword) && !accountValidationService.validateUserCredentials(username, password)) {
            UserInfo userInfo = new UserInfo(username, password);
            userService.saveUserInfo(hashingService.generateSaltHashedPassword(userInfo));
        } else if (accountValidationService.validateUserCredentials(username, password)) {
            return "redirect:/register?error=Account already exists"; // Redirect to registration page with error message
        } else{
            return "redirect:/register?error=Passwords do not match"; // Redirect to registration page with error message
        }
        return "login";
    }
}
