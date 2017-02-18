package pl.akademiakodu.dao;


import org.springframework.stereotype.Repository;
import pl.akademiakodu.model.Gif;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GifDaoImpl implements GifDao {

    @PersistenceContext
    private EntityManager entityManager;


    public GifDaoImpl() {
    }

    @Override
    @Transactional
    public void save(Gif gif) {
        entityManager.persist(gif);
    }

    @Override
    @Transactional
    public Gif findByName(String name) {
        Gif result = (Gif) entityManager.createQuery("FROM Gif E WHERE E.name LIKE :name")
                .setParameter("name", name+".gif")
                .getSingleResult();
        return result;
    }

    @Override
    @Transactional
    public List<Gif> findByCategoryId(int categoryId) {
        return entityManager.createQuery("FROM Gif E WHERE E.categoryId=" + categoryId).getResultList();
    }

    @Override
    @Transactional
    public List getAllGifs() {
        return entityManager.createQuery("SELECT p FROM Gif p").getResultList();
    }

    public List<Gif> findFavorites() {
        return entityManager.createQuery("FROM Gif E WHERE E.favorite=" + 1).getResultList();
    }

    @Override
    @Transactional
    public List<Gif> findpart(String name) {
        List<Gif> ALL_GIFS = new ArrayList<>(entityManager.createQuery("SELECT p FROM Gif p").getResultList());
        List<Gif> gifs = new ArrayList<>();

        for (Gif gif : ALL_GIFS) {
            if (gif.getName().contains(name)) {
                gifs.add(gif);
            }
        }
        return gifs;
    }
}
