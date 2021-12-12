package com.example.springboot_book.model.student;

import com.example.springboot_book.model.card.Card;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String identity;

    @OneToMany(targetEntity = Card.class, mappedBy = "student")
    @JsonBackReference
    private List<Card> cardList;

    public Student() {
    }

    public Student(Long id, String name, String identity) {
        this.id = id;
        this.name = name;
        this.identity = identity;
    }

    public Student(String name, String identity, List<Card> cardList) {
        this.name = name;
        this.identity = identity;
        this.cardList = cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Student(String name, String identity) {
        this.name = name;
        this.identity = identity;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
