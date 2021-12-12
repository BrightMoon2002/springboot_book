package com.example.springboot_book.controller.card;

import com.example.springboot_book.model.book.Book;
import com.example.springboot_book.model.card.Card;
import com.example.springboot_book.model.student.Student;
import com.example.springboot_book.service.book.IBookService;
import com.example.springboot_book.service.card.ICardService;
import com.example.springboot_book.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private ICardService cardService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ModelAndView showAll() {
        ModelAndView model = new ModelAndView("/card/list");
        Iterable<Card> cards = cardService.findAll();
        model.addObject("cardList", cards);
        if (cards != null) {
            model.addObject("message", "");
        } else {
            model.addObject("message", "No card on the list");
        }
        return model;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Card>> getAll() {
        return new ResponseEntity<>(cardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> showEditForm(@PathVariable Long id) {
        Card card = new Card();
        Optional<Book> book = bookService.findById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            card.setBook(book.get());
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Card> borrowBook(@RequestBody Card card) {
        Optional<Student> student = studentService.findById(card.getStudent().getId());
        if (student.isPresent()) {
            card.setDateBorrow(LocalDate.now());
            int code = (int) (Math.floor(Math.random()*99999) + 10000);
            String codeCard = "HDB"+code;
            card.setCode(codeCard);
            card.setStatus(true);
            Book book = bookService.findById(card.getBook().getId()).get();
            int quantity = book.getQuantity() - 1;
            book.setQuantity(quantity);
            card.setBook(book);
            cardService.save(card);
            bookService.save(book);
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        } else {
            card = null;
            return new ResponseEntity<>(card, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView model = new ModelAndView("/error-404");
        model.addObject("message", "No student have this id");
        return model;
    }
}

