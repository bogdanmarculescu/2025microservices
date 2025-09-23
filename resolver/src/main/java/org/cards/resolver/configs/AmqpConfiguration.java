package org.cards.resolver.configs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AmqpConfiguration {
    // exchange
    @Bean
    public TopicExchange topicExchange(
            @Value("${amqp.exchange.name}") final String exchangeName
    ) {
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)
                .build();
    }

    // queue
    @Bean
    public Queue roundQueue(
            @Value("${amqp.queue.name}") final String queueName
    ) {
        return  QueueBuilder
                .durable(queueName)
                .build();
    }

    // bindings
    @Bean
    public Binding resolverBinding(
            final TopicExchange exchange,
            final Queue queue
    ){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("rounds.complete");
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter
    ) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        return factory;
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new org.springframework.messaging.converter.MappingJackson2MessageConverter());
        return factory;
    }

    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
            final MessageHandlerMethodFactory messageHandlerMethodFactory
    ) {
        return c -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}
