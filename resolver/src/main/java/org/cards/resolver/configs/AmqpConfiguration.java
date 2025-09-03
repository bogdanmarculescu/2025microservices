package org.cards.resolver.configs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AmqpConfiguration {

    // exchanges - same as sender

    @Bean
    public TopicExchange resolverTopicExchange(
            @Value("${amqp.exchange.name}") final String exchangeName
    ) {
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)
                .build();
    }

    // bindings
    @Bean
    public Binding resolverBinding(
            final Queue queue,
            final TopicExchange exchange
    ){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("round.solved");
    }

    // queues
    @Bean
    public Queue resolverQueue(
            @Value("${amqp.queue.name}") final String queueName
    ){
        return QueueBuilder
                .durable(queueName)
                .build();

    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        //create default factory
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();

        // define a converter based on Jackson
        final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        // register a new module for the coverter de just defined
        messageConverter.getObjectMapper().registerModule(
                new ParameterNamesModule(JsonCreator.Mode.PROPERTIES)
        );
        // connect the default factory to the new converter
        messageHandlerMethodFactory.setMessageConverter(messageConverter);
        // return the factory
        return messageHandlerMethodFactory;
    }
}
