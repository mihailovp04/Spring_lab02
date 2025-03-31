package com.example.librarymanagement.controller;

import com.example.librarymanagement.dto.LibraryDTO;
import com.example.librarymanagement.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping
    public ResponseEntity<LibraryDTO> create(@RequestBody LibraryDTO dto) {
        return ResponseEntity.ok(libraryService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> update(@PathVariable Long id, @RequestBody LibraryDTO dto) {
        return ResponseEntity.ok(libraryService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<LibraryDTO>> getAll() {
        return ResponseEntity.ok(libraryService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        libraryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}