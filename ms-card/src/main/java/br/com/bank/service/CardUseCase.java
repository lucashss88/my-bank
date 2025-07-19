package br.com.bank.service;

import br.com.bank.model.Card;

import java.util.List;

public interface CardUseCase {
    void processCardRequest(String document);
    List<Card> getAll();
}