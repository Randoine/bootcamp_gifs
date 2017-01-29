package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.akademiakodu.repository.CategoryRepository;
import pl.akademiakodu.repository.GifRepository;

@Controller
public class CategoryController {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/categories")
    public String listCategories(ModelMap modelMap){
        modelMap.addAttribute("categories", categoryRepository.getAllCategoriees());
        return "categories";
    }

    @GetMapping("/category/{id}")
    public String categoryDetails(@PathVariable int id, ModelMap modelMap){
        modelMap.put("category", categoryRepository.findId(id));
        modelMap.put("gifs", gifRepository.findByCategoryId(id));
        return "category";
    }
}
