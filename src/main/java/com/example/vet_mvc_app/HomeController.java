package com.example.vet_mvc_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String simplePage(Model model) {
        model.addAttribute("pageTitle", "Home Page");
        model.addAttribute("view", "HomePage");
        model.addAttribute("page", "home");
        return "/Layout";
    }

}