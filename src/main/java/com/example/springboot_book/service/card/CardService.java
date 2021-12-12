package com.example.springboot_book.service.card;

import com.example.springboot_book.model.card.Card;
import com.example.springboot_book.repo.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService implements ICardService {
    @Autowired
    private ICardRepository cardRepository;
    @Override
    public Iterable<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> findById(Long id) throws IllegalArgumentException {
        return cardRepository.findById(id);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void remove(Card card) {
        cardRepository.delete(card);
    }

    @Override
    public Page<Card> findAllByNameContaining(String name, Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    @Override
    public Page<Card> findAll(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    @Override
    public Card saveT(Card card) {
        return cardRepository.save(card);
    }
}
