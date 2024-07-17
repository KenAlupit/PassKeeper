package com.alupit.ciit.passkeeper.controller;

import com.alupit.ciit.passkeeper.entity.PasswordInfo;
import com.alupit.ciit.passkeeper.entity.PasswordInfoKeys;
import com.alupit.ciit.passkeeper.model.UserSession;
import com.alupit.ciit.passkeeper.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@Controller
public class crudController {

    @Autowired
    private PasswordInfoService passwordInfoService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private HashingService hashingService;

    @Autowired
    private PasswordInfoKeyService  passwordInfoKeyService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @Autowired
    private CacheService cacheService;

    @GetMapping("/showNewPasswordInfoForm")
    public String showNewPasswordInfoForm(Model model) {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }

        PasswordInfo passwordInfo = new PasswordInfo();
        model.addAttribute("session_username", session_username);
        model.addAttribute("passwordInfo", passwordInfo);
        return "add";
    }
    @PostMapping("/savePasswordInfo")
    public String savePasswordInfo(@ModelAttribute("passwordInfo") PasswordInfo passwordInfo, HttpServletResponse response) throws Exception {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);
        // Generate salted and hashed password using HashingService
        passwordInfoService.savePasswordInfo(hashingService.generateEncryptedPassword(passwordInfo));
        //passwordInfoService.savePasswordInfo(passwordInfo);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model, HttpServletResponse response) throws Exception {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);
        //decrypt the stored password for viewing
        PasswordInfo passwordInfo = passwordInfoService.getPasswordInfobyid(id);
        String encryptedPassword = passwordInfo.getPassword();
        SecretKey secretKey = passwordInfoKeyService.getPasswordInfoKeybyid(id).getSecretKey();
        String decryptedPassword = encryptionService.decryptData(encryptedPassword, secretKey);

        passwordInfo.setPassword(decryptedPassword);
        model.addAttribute("passwordInfo", passwordInfo);
        return "update";
    }

    @PostMapping("/showFormForUpdate/{id}")
    public String updatePassword(@PathVariable(value = "id") int id, @ModelAttribute("passwordInfo") PasswordInfo passwordInfo, HttpServletResponse response) throws Exception {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);
        passwordInfo.setPasswordId(id);
        PasswordInfoKeys passwordInfoKeys = passwordInfoKeyService.getPasswordInfoKeybyid(id);
        // updated both passwordinfo and passwordinfokeys
        passwordInfoService.savePasswordInfo(hashingService.updatePassword(passwordInfo, passwordInfoKeys));
        return "redirect:/";
    }
    @GetMapping("/deletePasswordInfo/{id}")
    public String deletePasswordInfo(@PathVariable(value = "id") int id) {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        passwordInfoKeyService.deletePasswordInfoKey(id);
        passwordInfoService.deletePasswordInfo(id);
        return "redirect:/";
    }

    @GetMapping("/generate-password")
    @ResponseBody
    public String generatePassword() {
        return passwordGeneratorService.generatePassword();
    }
}
