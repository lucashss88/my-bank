package br.com.bank.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureMessageBrokerRabbitMQ {

    public static final String EXCHANGE_NAME = "cards_exchange";
    public static final String QUEUE_SUCCESS = "card_success";
    public static final String QUEUE_ERROR = "card_error";

    @Bean
    public Queue queueSuccess() {
        return new Queue(QUEUE_SUCCESS, true);
    }

    @Bean
    public Queue queueError() {
        return new Queue(QUEUE_ERROR, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingSuccess(Queue queueSuccess, DirectExchange exchange) {
        return BindingBuilder.bind(queueSuccess).to(exchange).with("success");
    }

    @Bean
    public Binding bindingError(Queue queueError, DirectExchange exchange) {
        return BindingBuilder.bind(queueError).to(exchange).with("error");
    }
}