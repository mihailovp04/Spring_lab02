package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.LibraryDTO;
import com.example.librarymanagement.entity.Library;
import com.example.librarymanagement.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryDTO create(LibraryDTO dto) {
        Library library = new Library();
        library.setName(dto.getName());
        library.setBookIds(dto.getBookIds());
        library = libraryRepository.save(library);
        dto.setId(library.getId());
        return dto;
    }

    public LibraryDTO update(Long id, LibraryDTO dto) {
        Library library = libraryRepository.findById(id).orElseThrow();
        library.setName(dto.getName());
        library.setBookIds(dto.getBookIds());
        libraryRepository.save(library);
        return dto;
    }

    public LibraryDTO get(Long id) {
        Library library = libraryRepository.findById(id).orElseThrow();
        LibraryDTO dto = new LibraryDTO();
        dto.setId(library.getId());
        dto.setName(library.getName());
        dto.setBookIds(library.getBookIds());
        return dto;
    }

    public List<LibraryDTO> getAll() {
        return libraryRepository.findAll().stream().map(library -> {
            LibraryDTO dto = new LibraryDTO();
            dto.setId(library.getId());
            dto.setName(library.getName());
            dto.setBookIds(library.getBookIds());
            return dto;
        }).toList();
    }

    public void delete(Long id) {
        libraryRepository.deleteById(id);
    }
}