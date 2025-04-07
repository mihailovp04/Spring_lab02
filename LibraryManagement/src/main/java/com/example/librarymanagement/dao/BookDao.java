package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Book;

import java.util.List;

public interface BookDao {
    Book save(Book book);
    Book findById(Long id);
    List<Book> findAll();
    void update(Book book);
    void delete(Long id);
}