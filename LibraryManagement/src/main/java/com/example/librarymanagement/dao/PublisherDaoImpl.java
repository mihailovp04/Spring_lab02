package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PublisherDaoImpl implements PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Publisher save(Publisher publisher) {
        if (publisher.getId() == null) {
            entityManager.persist(publisher);
        } else {
            entityManager.merge(publisher);
        }
        return publisher;
    }

    @Override
    public Publisher findById(Long id) {
        return entityManager.find(Publisher.class, id);
    }

    @Override
    public List<Publisher> findAll() {
        return entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class).getResultList();
    }

    @Override
    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    @Override
    public void delete(Long id) {
        Publisher publisher = findById(id);
        if (publisher != null) {
            entityManager.remove(publisher);
        }
    }
}