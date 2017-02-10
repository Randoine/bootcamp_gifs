package pl.akademiakodu.dao;

import pl.akademiakodu.model.Gif;

import java.util.List;

public interface GifDao {
    void save(Gif gif);
    Gif findByName(String name);
    List<Gif> findByCategoryId(int categoryId);
    List<Gif> getAllGifs();
    List<Gif> findFavorites();
    List<Gif> findpart(String name);

}
