package com.alupit.ciit.passkeeper.controller;

import com.alupit.ciit.passkeeper.model.UserSession;
import com.alupit.ciit.passkeeper.service.AccountValidationService;
import com.alupit.ciit.passkeeper.service.CacheService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private AccountValidationService accountValidationService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private CacheService cacheService;

    // Handler method for GET requests to root endpoint ("/")
    @GetMapping("/")
    public String home(HttpSession session, HttpServletResponse response) {
        // Redirect to login page if user is not logged in
        if(userSession.getUsername()==null){
            return "redirect:/login";
        }

        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);

        // Store user session with the username
        session.setAttribute("userSession", userSession);

        // Return the name of the view (home.jsp in this case)
        return "home";
    }

    // Handler method for GET requests to "/login" endpoint
    @GetMapping("/login")
    public String showLoginForm() {
        // Return the name of the view (login.jsp in this case)
        return "login";
    }

    // Handler method for POST requests to "/login" endpoint
    @PostMapping("/login")
    public String login(String username, String password) throws Exception {
        // Validate user credentials using PasswordService
        if (accountValidationService.validateUserCredentials(username,password)) {
            // Set the username in userSession upon successful login
            userSession.setUsername(username);
            return "redirect:/"; // Redirect to root endpoint ("/")
        } else {
            return "redirect:/login?error=invalid username or password"; // Redirect to login page with error message
        }
    }
}
