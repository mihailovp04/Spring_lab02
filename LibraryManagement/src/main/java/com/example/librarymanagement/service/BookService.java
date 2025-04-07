package com.example.librarymanagement.service;

import com.example.librarymanagement.dao.AuthorDao;
import com.example.librarymanagement.dao.BookDao;
import com.example.librarymanagement.dao.CategoryDao;
import com.example.librarymanagement.dao.PublisherDao;
import com.example.librarymanagement.dto.BookDTO;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;
    private final CategoryDao categoryDao;

    public BookDTO create(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(authorDao.findById(dto.getAuthorId()));
        book.setPublisher(publisherDao.findById(dto.getPublisherId()));
        book.setCategories(categoryDao.findAllByIds(dto.getCategoryIds()));
        book = bookDao.save(book);
        dto.setId(book.getId());
        return dto;
    }

    public BookDTO update(Long id, BookDTO dto) {
        Book book = bookDao.findById(id);
        if (book == null) throw new RuntimeException("Book not found");
        book.setTitle(dto.getTitle());
        book.setAuthor(authorDao.findById(dto.getAuthorId()));
        book.setPublisher(publisherDao.findById(dto.getPublisherId()));
        book.setCategories(categoryDao.findAllByIds(dto.getCategoryIds()));
        bookDao.update(book);
        return dto;
    }

    public BookDTO get(Long id) {
        Book book = bookDao.findById(id);
        if (book == null) throw new RuntimeException("Book not found");
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setPublisherId(book.getPublisher().getId());
        dto.setCategoryIds(book.getCategories().stream().map(Category::getId).toList());
        return dto;
    }

    public List<BookDTO> getAll() {
        return bookDao.findAll().stream().map(book -> {
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
        bookDao.delete(id);
    }
}