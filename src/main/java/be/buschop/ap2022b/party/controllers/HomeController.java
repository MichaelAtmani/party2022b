package be.buschop.ap2022b.party.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home", "/home/"})
    public String home (){
        return "home";
    }

    @GetMapping("/about")
    public String about (){
        return "about";
    }

}
