package br.com.bank.external;

import br.com.bank.service.CardUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static br.com.bank.config.ConfigureMessageBrokerRabbitMQ.EXCHANGE_NAME;

@Component
public class CardRequestListener {

    private final CardUseCase cardUseCase;
    private final RabbitTemplate rabbitTemplate;


    public CardRequestListener(CardUseCase cardUseCase, RabbitTemplate rabbitTemplate) {
        this.cardUseCase = cardUseCase;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "requestCard")
    public void receive(String document) {
        try {
            cardUseCase.processCardRequest(document);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "success", "Card for document " + document + " processed successfully.");
        } catch (Exception e) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "error", "Error processing card for document " + document + ": " + e.getMessage());
        }
    }
}