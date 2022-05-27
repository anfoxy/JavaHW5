package com.netcracker.controller;

import com.netcracker.service.UserService;
import com.netcracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return "/index";
    }

    @GetMapping("/user")
    public String personForm(Model model){
        model.addAttribute("user",new User());
        return "user";
    }

    @GetMapping("/search")
    public String userSearchForm(Model model){
        model.addAttribute("user",new User());
        return "search";
    }

    @GetMapping("/true-result")
    public String trueResult(@ModelAttribute User user) {
        System.out.println(user.toString());
        return "true-result";
    }

    @PostMapping("/user")
    public String personSubmit(@ModelAttribute User user) {
        System.out.println(user.toString());
            userService.fileWrite(user);
            return "result-user";

    }
    @PostMapping("/search")
    public String userSearchSubmit(@ModelAttribute User user, Model model, HttpServletRequest request, HttpServletResponse response) {
        User tmp = userService.fileCheckUser(user);
       if(tmp==null){
        return "false-resylt";
       } else {
           Date date = new Date();
           user.personSet(tmp);
           model.addAttribute("time",date.toString() );
           model.addAttribute("agent", request.getHeader("User-Agent"));
           Cookie cookie = new Cookie("mail", user.getEmail());
           cookie.setMaxAge(1000);
           cookie.setSecure(true);
           response.addCookie(cookie);
           return "true-result";
       }
    }

    @GetMapping("/send-email")
    public String mail (@CookieValue("mail") String cook) {
        System.out.println(cook);
        userService.mailSend(cook);
        return "send-email";
    }

}
