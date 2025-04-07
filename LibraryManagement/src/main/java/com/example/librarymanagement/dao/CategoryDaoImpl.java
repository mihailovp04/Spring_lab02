package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category save(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        } else {
            entityManager.merge(category);
        }
        return category;
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public List<Category> findAllByIds(List<Long> ids) {
        return entityManager.createQuery("SELECT c FROM Category c WHERE c.id IN :ids", Category.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        if (category != null) {
            entityManager.remove(category);
        }
    }
}