package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Publisher;

import java.util.List;

public interface PublisherDao {
    Publisher save(Publisher publisher);
    Publisher findById(Long id);
    List<Publisher> findAll();
    void update(Publisher publisher);
    void delete(Long id);
}