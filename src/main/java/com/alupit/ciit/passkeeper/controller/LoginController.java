package com.alupit.ciit.passkeeper.controller;

import com.alupit.ciit.passkeeper.entity.PasswordInfo;
import com.alupit.ciit.passkeeper.entity.PasswordInfoKeys;
import com.alupit.ciit.passkeeper.entity.UserAccount;
import com.alupit.ciit.passkeeper.model.UserSession;
import com.alupit.ciit.passkeeper.service.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private AccountValidationService accountValidationService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private PasswordInfoService passwordInfoService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private PasswordInfoKeyService passwordInfoKeyService;

    // Handler method for GET requests to root endpoint ("/")
    @GetMapping("/")
    public String home(HttpSession session, HttpServletResponse response, Model model) throws Exception {
        String session_username = userSession.getUsername();
        List<PasswordInfo> passwordInfoList = new ArrayList<>();

        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }

        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);
        // iterate through all password info
        for (PasswordInfo i : passwordInfoService.getAllPasswordInfo()) {
            // if session username is the same with the password_owner of each i
            if (session_username.equals(i.getPasswordOwner())) {
                //iterate through all password info keys
                for (PasswordInfoKeys k : passwordInfoKeyService.getAllPasswordInfoKeys()){
                    // if the ids match
                    Integer KeyID = k.getKeyId(); //type int cannot have methods
                    Integer PassID = i.getPasswordId();
                    if (KeyID.equals(PassID)){
                        String decrypted_Password = encryptionService.decryptData(i.getPassword(), k.getSecretKey());
                        i.setPassword(decrypted_Password);
                        // add each instance of i to the list with the decrypted password
                        passwordInfoList.add(i);
                    }
                }

            }
        }
        // Store user session with the username
        session.setAttribute("userSession", userSession);

        // Store passwordinfoList into a model
        model.addAttribute("passwordInfoList", passwordInfoList);
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

    @GetMapping("/logout")//try
    public String logout() {
        userSession.setUsername(null);
        return "redirect:/login";
    }
}
