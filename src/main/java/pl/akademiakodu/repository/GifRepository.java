package pl.akademiakodu.repository;

import org.springframework.stereotype.Repository;
import pl.akademiakodu.model.Gif;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GifRepository {

    private static final List<Gif> ALL_GIFS = Arrays.asList(
            new Gif("android-explosion", "Chris Ramacciotti", false, 1),
            new Gif("ben-and-mike", "Ben Jakuben", true, 3),
            new Gif("book-dominos", "Craig Dennis", false, 2),
            new Gif("compiler-bot", "Ada Lovelace", true, 3),
            new Gif("cowboy-coder", "Grace Hopper", false, 1),
            new Gif("infinite-andrew", "Marissa Mayer", true, 3)
    );

    public Gif findByName(String name){
        for (Gif gif : ALL_GIFS){
            if ( gif.getName().equals(name)){
                return gif;
            }
        }
        return null; //prawidlowo powinnismy zwrocic new gif lub wyjątek
    }

    public List<Gif> findByCategoryId(int categoryId){
        List<Gif> gifs = new ArrayList<>();
        for (Gif gif : ALL_GIFS){
            if (gif.getCategoryId()==categoryId){
                gifs.add(gif);
            }
        }
        return gifs;
    }

    public List<Gif> getAllGifs(){
    return ALL_GIFS;
    }

    public List<Gif> findFavorites(){
        List<Gif> favourites = new ArrayList<>();
        for (Gif gif : ALL_GIFS){
            if (gif.isFavorite()){
                favourites.add(gif);
            }
        }
        return favourites;
    }

    public List<Gif> findpart(String name){
        List<Gif> gifs = new ArrayList<>();
        for (Gif gif : ALL_GIFS){
            if (gif.getName().contains(name)){
                gifs.add(gif);
            }
        }
        return gifs; //prawidlowo powinnismy zwrocic new gif lub wyjątek
    }
}
