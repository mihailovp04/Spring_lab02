package com.example.librarymanagement.service;

import com.example.librarymanagement.dao.LibraryDao;
import com.example.librarymanagement.dto.LibraryDTO;
import com.example.librarymanagement.entity.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryDao libraryDao;

    public LibraryDTO create(LibraryDTO dto) {
        Library library = new Library();
        library.setName(dto.getName());
        library.setBookIds(dto.getBookIds());
        library = libraryDao.save(library);
        dto.setId(library.getId());
        return dto;
    }

    public LibraryDTO update(Long id, LibraryDTO dto) {
        Library library = libraryDao.findById(id);
        if (library == null) throw new RuntimeException("Library not found");
        library.setName(dto.getName());
        library.setBookIds(dto.getBookIds());
        libraryDao.update(library);
        return dto;
    }

    public LibraryDTO get(Long id) {
        Library library = libraryDao.findById(id);
        if (library == null) throw new RuntimeException("Library not found");
        LibraryDTO dto = new LibraryDTO();
        dto.setId(library.getId());
        dto.setName(library.getName());
        dto.setBookIds(library.getBookIds());
        return dto;
    }

    public List<LibraryDTO> getAll() {
        return libraryDao.findAll().stream().map(library -> {
            LibraryDTO dto = new LibraryDTO();
            dto.setId(library.getId());
            dto.setName(library.getName());
            dto.setBookIds(library.getBookIds());
            return dto;
        }).toList();
    }

    public void delete(Long id) {
        libraryDao.delete(id);
    }
}