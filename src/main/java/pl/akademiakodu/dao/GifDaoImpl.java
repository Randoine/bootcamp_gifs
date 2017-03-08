package pl.akademiakodu.dao;



import org.springframework.stereotype.Repository;
import pl.akademiakodu.model.Category;
import pl.akademiakodu.model.Gif;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
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
    public Long saveAndGetId(Gif gif){
        entityManager.persist(gif);
        entityManager.flush();
        return gif.getId();
    }

    @Override
    @Transactional
    public Gif findByTitle(String title) throws NonUniqueResultException {
        return (Gif) entityManager.createQuery("FROM Gif WHERE title LIKE :title")
                .setParameter("title", title)
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
    public List<Gif> findByCategory(Category category) {
        return entityManager.createQuery("FROM Gif WHERE category_id=" + category.getId()).getResultList();
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
        return findByTitle(name).getId();
    }

    @Override
    @Transactional
    public void delete(Gif gif) {
        entityManager.remove(gif);
    }

}
