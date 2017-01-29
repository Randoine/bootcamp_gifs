package pl.akademiakodu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GifController {

    @GetMapping("/")
    public String listGifs(){
        return "home";
    }

}
