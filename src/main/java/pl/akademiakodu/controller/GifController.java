package pl.akademiakodu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.akademiakodu.model.Gif;
import pl.akademiakodu.repository.GifRepository;

@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/")
    public String listGifs() {
        return "home";
    }

    @GetMapping("/gif")
    public String gifDetails(ModelMap modelMap) {
        Gif gif = gifRepository.findByName("android-explosion");
        modelMap.put("gif", gif);
        return "gif-details";
    }

}
