package pl.akademiakodu.dao;


import org.springframework.stereotype.Repository;
import pl.akademiakodu.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List getAllCategoriees() {
        return entityManager.
                createQuery("SELECT p FROM Category p").getResultList();
    }

    @Override
    @Transactional
    public Category findId(int id) {
        Category result = (Category) entityManager.
                createQuery("FROM Category E WHERE E.id="+ id).getSingleResult();
        return result;
    }

    @Override
    @Transactional
    public void save(Category category) {
        entityManager.persist(category);
    }
}
