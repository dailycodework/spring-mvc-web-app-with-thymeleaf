package com.dailycodework.sbend2endapplication.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sampson Alfred
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String homePage(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
