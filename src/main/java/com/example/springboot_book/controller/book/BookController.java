package com.example.springboot_book.controller.book;

import com.example.springboot_book.model.book.Book;
import com.example.springboot_book.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("")
    private ModelAndView showAll(){
        ModelAndView model = new ModelAndView("/book/list");
        Iterable<Book> books = bookService.findAll();
        model.addObject("bookList", books);
        if (books != null) {
           model.addObject("message", "");
        } else {
            model.addObject("message", "No book on the list");
        }
        return model;
    }
    @GetMapping("/")
    public ResponseEntity<Iterable<Book>> getAll() {
        Iterable<Book> books = bookService.findAll();
        if (books != null) {
            return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.NOT_FOUND);
        } else {
            bookService.remove(book.get());
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookService.save(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
