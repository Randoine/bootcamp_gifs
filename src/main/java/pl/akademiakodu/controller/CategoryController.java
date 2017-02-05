package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akademiakodu.dao.CategoryDao;
import pl.akademiakodu.dao.CategoryDaoImpl;
import pl.akademiakodu.model.Category;
import pl.akademiakodu.repository.CategoryRepository;
import pl.akademiakodu.repository.GifRepository;

@Controller
public class CategoryController {


    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/categories")
    public String listCategories(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryDao.getAllCategoriees());
        return "category/categories";
    }

    @GetMapping("/category/{id}")
    public String categoryDetails(@PathVariable int id, ModelMap modelMap) {
        modelMap.put("category", categoryDao.findId(id));
        modelMap.put("gifs", gifRepository.findByCategoryId(id));
        return "category/category";
    }

    @GetMapping("/category/add")
    public String categoryAdd( ModelMap modelMap){
        modelMap.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category category, ModelMap modelMap){
        categoryDao.save(category);
        return "redirect:categories";
    }
}
