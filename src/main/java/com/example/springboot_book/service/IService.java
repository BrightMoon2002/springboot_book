package com.example.springboot_book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IService<T> {

    Iterable<T> findAll();
    Optional<T> findById(Long id) throws IllegalArgumentException;
    void save(T t);

    void remove(T t);
    Page<T> findAllByNameContaining(String name, Pageable pageable);
    Page<T> findAll(Pageable pageable);
    T saveT(T t);
}
