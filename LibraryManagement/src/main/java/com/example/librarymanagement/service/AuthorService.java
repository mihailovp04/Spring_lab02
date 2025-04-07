package com.example.librarymanagement.service;

import com.example.librarymanagement.dao.AuthorDao;
import com.example.librarymanagement.dto.AuthorDTO;
import com.example.librarymanagement.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDao authorDao;

    public AuthorDTO create(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author = authorDao.save(author);
        dto.setId(author.getId());
        return dto;
    }

    public AuthorDTO update(Long id, AuthorDTO dto) {
        Author author = authorDao.findById(id);
        if (author == null) throw new RuntimeException("Author not found");
        author.setName(dto.getName());
        authorDao.update(author);
        return dto;
    }

    public AuthorDTO get(Long id) {
        Author author = authorDao.findById(id);
        if (author == null) throw new RuntimeException("Author not found");
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

    public List<AuthorDTO> getAll() {
        return authorDao.findAll().stream().map(author -> {
            AuthorDTO dto = new AuthorDTO();
            dto.setId(author.getId());
            dto.setName(author.getName());
            return dto;
        }).toList();
    }

    public void delete(Long id) {
        authorDao.delete(id);
    }
}