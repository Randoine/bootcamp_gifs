package pl.akademiakodu.dao;

import pl.akademiakodu.model.Category;
import pl.akademiakodu.model.Gif;

import java.util.List;

public interface GifDao {
    void save(Gif gif);
    Gif findByTitle(String name);
    Gif findById(Long id);
    Long getId(String name);
    List<Gif> findByCategory(Category category);
    List<Gif> getAllGifs();
    List<Gif> findFavorites();
    List<Gif> findpart(String name);
    void edit (Gif gif);
    void delete (Gif gif);


}
