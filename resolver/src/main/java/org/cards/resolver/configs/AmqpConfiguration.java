package org.cards.resolver.configs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
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
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {

        // create a  default factory
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();

        // jackson for conversion - converter

        final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.getObjectMapper().registerModule(
                new ParameterNamesModule(JsonCreator.Mode.PROPERTIES)
        );
        // connect my converter to the factory
        messageHandlerMethodFactory.setMessageConverter(messageConverter);

        // return  factory
        return messageHandlerMethodFactory;
    }

    // the message handler factory needs to be communicated to the rabbit listener
    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
            final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}
