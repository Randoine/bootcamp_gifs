package pl.akademiakodu.controller;


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.dao.CategoryDao;
import pl.akademiakodu.dao.GifDao;
import pl.akademiakodu.model.Category;
import pl.akademiakodu.model.Gif;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class GifController {

    @Autowired
    private GifDao gifDao;
    @Autowired
    private CategoryDao categoryDao;

    private static final String UPLOADS_DIR = "//uploads//";

    @GetMapping("/")
    public String listGifs(ModelMap modelMap) {
        modelMap.addAttribute("gif", new Gif());
        modelMap.addAttribute("gifs", gifDao.getAllGifs());
        return "home";
    }

    @GetMapping("/favorites")
    public String gifFavourites(ModelMap modelMap) {
        modelMap.addAttribute("gifs", gifDao.findFavorites());
        return "favorites";
    }

    @GetMapping("/gif/{id}")
    public String gifDetails(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("gif", gifDao.findById(id));
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
        String realPathtoUploads = request.getServletContext().getRealPath(UPLOADS_DIR);
        fileUpload.transferTo(new File(realPathtoUploads + fileUpload.getOriginalFilename()));
        gif.setName(FilenameUtils.removeExtension(fileUpload.getOriginalFilename()));
        gif.setTitle(gif.getName());
        gif.setCategoryId(1);
        gif.setFavorite(false);
        gif.setUsername("Anonymous");
        //TODO: Change hardcoded username to variable after Spring Security login implementation
        gif.setPath(realPathtoUploads + fileUpload.getOriginalFilename());
        gifDao.save(gif);


        redirectAttributes.addFlashAttribute("message", "You have succesfully uploaded " + gif.getName() + "!");
        return "redirect:upload";
    }

    @GetMapping("/gif/{id}/edit")
    public String gifEdit(@PathVariable Long id, ModelMap modelMap) {
        System.out.println(gifDao.findById(id));
        modelMap.addAttribute("gif", gifDao.findById(id));
        modelMap.addAttribute("gifNew", new Gif());
        modelMap.addAttribute("categories", categoryDao.getAllCategoriees());
        return "gif-edit";
    }

    @PostMapping("/gif/{id}/changeTitle")
    public String changeName(@PathVariable Long id, @ModelAttribute Gif gifNew, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        Gif gif = gifDao.findById(id);
        gif.setTitle(gifNew.getTitle());
        gifDao.edit(gif);
        redirectAttributes.addFlashAttribute("message", "You have successfully renamed gif!");
        return "redirect:edit";
    }

    @GetMapping("/gif/{id}/doFavorite")
    public String doFavorite(@PathVariable Long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        Gif gif = gifDao.findById(id);
        gif.setFavorite(true);
        gifDao.edit(gif);
        redirectAttributes.addFlashAttribute("message", "This gif is your favorite now!");
        return "redirect:edit";
    }

    @GetMapping("/gif/{id}/unFavorite")
    public String unFavorite(@PathVariable Long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        Gif gif = gifDao.findById(id);
        gif.setFavorite(false);
        gifDao.edit(gif);
        redirectAttributes.addFlashAttribute("message", "This gif isn't your favorite now!");
        return "redirect:edit";
    }

    @GetMapping("/gif/{gifid}/setCategory/{categoryid}")
    public String setCategory(@PathVariable Long gifid, @PathVariable int categoryid, ModelMap modelMap,
                              RedirectAttributes redirectAttributes) {
        Gif gif = gifDao.findById(gifid);
        Category category = categoryDao.findId(categoryid);
        gif.setCategoryId(categoryid);
        gifDao.edit(gif);
        redirectAttributes.addFlashAttribute("message",
                "Gif " + gif.getTitle() + " is now in " + category.getName() + " category !");
        return "redirect:/gif/{gifid}/edit";
    }
}
