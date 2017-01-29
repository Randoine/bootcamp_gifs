package pl.akademiakodu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.akademiakodu.model.Gif;

@Controller
public class GifController {

    @GetMapping("/")
    public String listGifs() {
        return "home";
    }

    @GetMapping("/gif")
    public String gifDetails(ModelMap modelMap) {
        Gif gif = new Gif("android-explosion", "Rav", true);
        modelMap.put("gif", gif);
        return "gif-details";
    }

}
