package com.alupit.ciit.passkeeper.controller;

import com.alupit.ciit.passkeeper.entity.PasswordInfo;
import com.alupit.ciit.passkeeper.model.UserSession;
import com.alupit.ciit.passkeeper.service.HashingService;
import com.alupit.ciit.passkeeper.service.PasswordInfoKeyService;
import com.alupit.ciit.passkeeper.service.PasswordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String savePasswordInfo(@ModelAttribute("passwordInfo") PasswordInfo passwordInfo) throws Exception {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        // Generate salted and hashed password using HashingService
        passwordInfoService.savePasswordInfo(hashingService.generateSaltHashedPassword(passwordInfo));
        //passwordInfoService.savePasswordInfo(passwordInfo);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        PasswordInfo passwordInfo = passwordInfoService.getPasswordInfobyid(id);
        model.addAttribute("passwordInfo", passwordInfo);
        return "update";
    }

    @PostMapping("/showFormForUpdate/{id}")
    public String updatePassword(@PathVariable(value = "id") int id, @ModelAttribute("passwordInfo") PasswordInfo passwordInfo) throws Exception {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        passwordInfo.setPasswordId(id);
        passwordInfoService.savePasswordInfo(hashingService.generateSaltHashedPassword(passwordInfo));
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
}
