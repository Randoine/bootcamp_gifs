package pl.akademiakodu.repository;

import org.springframework.stereotype.Repository;
import pl.akademiakodu.model.Category;

import java.util.Arrays;
import java.util.List;

@Repository
public class CategoryRepository {
    private static final List<Category> ALL_CATEGORIEES = Arrays.asList(
            new Category(1, "Gifs"),
            new Category(2, "Memes"),
            new Category(3, "Technology")
    );

    public List<Category> getAllCategoriees() {
        return ALL_CATEGORIEES;
    }

    public Category findId(int id) {
        for (Category category : ALL_CATEGORIEES) {
            if (category.getId() == id)
                return category;
        }
        return null;
    }
}
