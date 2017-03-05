package pl.akademiakodu.dao;


import org.springframework.stereotype.Repository;
import pl.akademiakodu.model.Category;
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
        return (Gif) entityManager.createQuery("FROM Gif E WHERE E.name LIKE :name")
                .setParameter("name", name+".gif")
                .getSingleResult();
    }

    @Override
    @Transactional
    public Gif findById(Long id) {
        return (Gif) entityManager.createQuery("FROM Gif E WHERE E.id= :id")
                .setParameter("id", id)
                .getSingleResult();
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
            if (gif.getName().contains(name) || gif.getTitle().contains(name)) {
                gifs.add(gif);
            }
        }
        return gifs;
    }

    @Override
    @Transactional
    public void edit(Gif gif) {entityManager.merge(gif);
    }

    @Override
    @Transactional
    public Long getId(String name) {
        return findByName(name).getId();
    }

    @Override
    @Transactional
    public void delete(Gif gif) {
        entityManager.remove(gif);
    }
}
