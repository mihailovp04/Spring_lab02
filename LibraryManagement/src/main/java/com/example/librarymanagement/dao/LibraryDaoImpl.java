package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Library;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LibraryDaoImpl implements LibraryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Library save(Library library) {
        if (library.getId() == null) {
            entityManager.persist(library);
        } else {
            entityManager.merge(library);
        }
        return library;
    }

    @Override
    public Library findById(Long id) {
        return entityManager.find(Library.class, id);
    }

    @Override
    public List<Library> findAll() {
        return entityManager.createQuery("SELECT l FROM Library l", Library.class).getResultList();
    }

    @Override
    public void update(Library library) {
        entityManager.merge(library);
    }

    @Override
    public void delete(Long id) {
        Library library = findById(id);
        if (library != null) {
            entityManager.remove(library);
        }
    }
}