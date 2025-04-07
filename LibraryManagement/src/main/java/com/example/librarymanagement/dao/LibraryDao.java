package com.example.librarymanagement.dao;

import com.example.librarymanagement.entity.Library;

import java.util.List;

public interface LibraryDao {
    Library save(Library library);
    Library findById(Long id);
    List<Library> findAll();
    void update(Library library);
    void delete(Long id);
}