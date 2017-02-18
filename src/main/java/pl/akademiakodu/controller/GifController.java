package pl.akademiakodu.controller;


import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.dao.GifDao;
import pl.akademiakodu.model.Gif;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class GifController {

    @Autowired
    private GifDao gifDao;
    private static final String UPLOADS_DIR = "//uploads//";

    @GetMapping("/")
    public String listGifs(ModelMap modelMap) {
        modelMap.addAttribute("gifs", gifDao.getAllGifs());
        modelMap.addAttribute("gif", new Gif());
        return "home";
    }

//    @GetMapping("/gif")
//    public String gifDetails(ModelMap modelMap) {
//        Gif gif = gifDao.findByName("android-explosion");
//        modelMap.put("gif", gif);
//        return "gif-details";
//    }

    @GetMapping("/favorites")
    public String gifFavourites(ModelMap modelMap) {
        modelMap.addAttribute("gifs", gifDao.findFavorites());
        return "favorites";
    }

    @GetMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        modelMap.put("gif", gifDao.findByName(name));
        return "gif-details";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Gif gif, ModelMap modelMap) {
        modelMap.addAttribute("gifs", gifDao.findpart(gif.getName()));
        return "home";
    }

    @GetMapping("/upload")
    public String showUploadForm(HttpServletRequest request) {
        return "file-upload";
    }

    @PostMapping("/doUpload")
    public String handleGifUpload(HttpServletRequest request,
                                   @RequestParam MultipartFile fileUpload,
                                   RedirectAttributes redirectAttributes) throws Exception {

        Gif gif = new Gif();
        System.out.println("Saving file: " + fileUpload.getOriginalFilename());
        String realPathtoUploads =  request.getServletContext().getRealPath(UPLOADS_DIR);
        fileUpload.transferTo(new File(realPathtoUploads+fileUpload.getOriginalFilename()));
        gif.setName(fileUpload.getOriginalFilename());
        gif.setCategoryId(1);
        gif.setFavorite(false);
        gif.setUsername("Anonymous");
        //TODO: Change hardcoded username to variable after Spring Security login implementation
        gif.setPath(realPathtoUploads+fileUpload.getOriginalFilename());
        gifDao.save(gif);


        redirectAttributes.addFlashAttribute("message", "You have succesfully uploaded " + gif.getName() + "!");
        return "redirect:upload";
    }
}
