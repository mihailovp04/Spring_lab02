package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Author;

import java.util.List;

public interface AuthorDao {
    Author save(Author author);
    Author findById(Long id);
    List<Author> findAll();
    void update(Author author);
    void delete(Long id);
}