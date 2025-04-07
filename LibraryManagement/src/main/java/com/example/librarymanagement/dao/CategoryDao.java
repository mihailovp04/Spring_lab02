package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category save(Category category);
    Category findById(Long id);
    List<Category> findAll();
    List<Category> findAllByIds(List<Long> ids);
    void update(Category category);
    void delete(Long id);
}