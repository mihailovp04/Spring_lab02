package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.BookDTO;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.Category; // Добавляем импорт
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.CategoryRepository;
import com.example.librarymanagement.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;

    public BookDTO create(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(authorRepository.findById(dto.getAuthorId()).orElseThrow());
        book.setPublisher(publisherRepository.findById(dto.getPublisherId()).orElseThrow());
        book.setCategories(categoryRepository.findAllById(dto.getCategoryIds()));
        book = bookRepository.save(book);
        dto.setId(book.getId());
        return dto;
    }

    public BookDTO update(Long id, BookDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(dto.getTitle());
        book.setAuthor(authorRepository.findById(dto.getAuthorId()).orElseThrow());
        book.setPublisher(publisherRepository.findById(dto.getPublisherId()).orElseThrow());
        book.setCategories(categoryRepository.findAllById(dto.getCategoryIds()));
        bookRepository.save(book);
        return dto;
    }

    public BookDTO get(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setPublisherId(book.getPublisher().getId());
        dto.setCategoryIds(book.getCategories().stream().map(Category::getId).toList());
        return dto;
    }

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream().map(book -> {
            BookDTO dto = new BookDTO();
            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthorId(book.getAuthor().getId());
            dto.setPublisherId(book.getPublisher().getId());
            dto.setCategoryIds(book.getCategories().stream().map(Category::getId).toList());
            return dto;
        }).toList();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}