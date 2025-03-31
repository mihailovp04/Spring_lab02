package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.AuthorDTO;
import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorDTO create(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author = authorRepository.save(author);
        dto.setId(author.getId());
        return dto;
    }

    public AuthorDTO update(Long id, AuthorDTO dto) {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setName(dto.getName());
        authorRepository.save(author);
        return dto;
    }

    public AuthorDTO get(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream().map(author -> {
            AuthorDTO dto = new AuthorDTO();
            dto.setId(author.getId());
            dto.setName(author.getName());
            return dto;
        }).toList();
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}