package br.com.bank.service.usecase;

import br.com.bank.model.Card;
import br.com.bank.repository.CardRepository;
import br.com.bank.service.CardUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardUseCaseImpl implements CardUseCase {

    private final CardRepository cardRepository;

    public CardUseCaseImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void processCardRequest(String document) {
        if (Integer.parseInt(document.substring(document.length() - 1)) % 2 != 0) {
            throw new RuntimeException("Odd document numbers are not allowed to create a card");
        }

        Card card = Card.builder()
                .document(document)
                .name("CARD FOR " + document)
                .flag("MASTERCARD")
                .cardLimit(BigDecimal.valueOf(1000))
                .build();
        cardRepository.save(card);
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }
}