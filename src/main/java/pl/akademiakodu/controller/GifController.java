package pl.akademiakodu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akademiakodu.model.Gif;
import pl.akademiakodu.repository.GifRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/")
    public String listGifs(ModelMap modelMap) {
        modelMap.addAttribute("gifs", gifRepository.getAllGifs());
        modelMap.addAttribute("gif", new Gif());
//        List<Gif> allGifs = gifRepository.getAllGifs();
//        modelMap.put("gifs",allGifs);
        return "home";

    }

    @GetMapping("/gif")
    public String gifDetails(ModelMap modelMap) {
        Gif gif = gifRepository.findByName("android-explosion");
        modelMap.put("gif", gif);
        return "gif-details";
    }

    @GetMapping("/favorites")
    public String gifFavourites(ModelMap modelMap) {
        modelMap.addAttribute("gifs", gifRepository.findFavorites());
        return "favorites";
    }

    @GetMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap){
        modelMap.put("gif", gifRepository.findByName(name));
        return "gif-details";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Gif gif, ModelMap modelMap){
        modelMap.addAttribute("gifs", gifRepository.findpart(gif.getName()));
        return "home";
    }

}
