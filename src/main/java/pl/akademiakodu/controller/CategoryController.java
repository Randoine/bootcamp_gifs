package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.akademiakodu.repository.CategoryRepository;

@Controller
public class CategoryController {


    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String listCategories(ModelMap modelMap){
        modelMap.addAttribute("categories", categoryRepository.getAllCategoriees());
        return "categories";
    }
}
