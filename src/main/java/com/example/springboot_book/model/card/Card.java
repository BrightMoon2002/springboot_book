package com.example.springboot_book.model.card;

import com.example.springboot_book.model.book.Book;
import com.example.springboot_book.model.student.Student;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_id")
    private Student student;


    private LocalDate dateBorrow;
    private LocalDate datePaid;
    private boolean status;

    public Card(Long id, String code, Book book, Student student) {
        this.id = id;
        this.code = code;
        this.book = book;
        this.student = student;
    }

    public Card() {
    }

    public Card(String code, Book book) {
        this.code = code;
        this.book = book;
    }

    public Card(Long id, String code, Book book, Student student, LocalDate dateBorrow, LocalDate datePaid, boolean status) {
        this.id = id;
        this.code = code;
        this.book = book;
        this.student = student;
        this.dateBorrow = dateBorrow;
        this.datePaid = datePaid;
        this.status = status;
    }

    public Card(String code, Book book, Student student, LocalDate dateBorrow, LocalDate datePaid, boolean status) {
        this.code = code;
        this.book = book;
        this.student = student;
        this.dateBorrow = dateBorrow;
        this.datePaid = datePaid;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(LocalDate dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
