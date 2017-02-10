package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.dao.CategoryDao;
import pl.akademiakodu.dao.GifDao;
import pl.akademiakodu.model.Category;
import pl.akademiakodu.repository.GifRepository;

@Controller
public class CategoryController {


    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GifDao gifDao;

    @GetMapping("/categories")
    public String listCategories(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryDao.getAllCategoriees());
        return "category/categories";
    }

    @GetMapping("/category/{id}")
    public String categoryDetails(@PathVariable int id, ModelMap modelMap) {
        modelMap.put("category", categoryDao.findId(id));
        modelMap.put("gifs", gifDao.findByCategoryId(id));
        return "category/category";
    }

    @GetMapping("/category/add")
    public String categoryAdd( ModelMap modelMap){
        modelMap.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category category, ModelMap modelMap, RedirectAttributes redirectAttributes){
        categoryDao.save(category);
        redirectAttributes.addFlashAttribute("message", "You have succesfully added "+ category.getName()+" category!");
        return "redirect:categories";
    }
}
