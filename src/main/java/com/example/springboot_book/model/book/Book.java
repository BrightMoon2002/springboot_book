package com.example.springboot_book.model.book;

import com.example.springboot_book.model.card.Card;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private int quantity;

    @OneToMany(targetEntity = Card.class, mappedBy = "book")
    @JsonBackReference
    private List<Card> cardList;

    public Book() {
    }

    public Book(Long id, String name, String author, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public Book(String name, String author, int quantity) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public Book(Long id, String name, String author, int quantity, List<Card> cardList) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.cardList = cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
