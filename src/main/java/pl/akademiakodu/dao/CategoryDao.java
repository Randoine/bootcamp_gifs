package pl.akademiakodu.dao;


import pl.akademiakodu.model.Category;

import java.util.List;

public interface CategoryDao {
    List getAllCategoriees();
    Category findId(int id);
    void save(Category category);
    void editName(Category category);

}
