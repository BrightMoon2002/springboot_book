package com.example.springboot_book.service.book;

import com.example.springboot_book.model.book.Book;
import com.example.springboot_book.repo.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) throws IllegalArgumentException {
        return bookRepository.findById(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);

    }

    @Override
    public void remove(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Page<Book> findAllByNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book saveT(Book book) {
        return bookRepository.save(book);
    }
}
