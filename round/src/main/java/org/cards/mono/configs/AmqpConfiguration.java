package org.cards.mono.configs;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    @Bean
    public TopicExchange automaPlayer(
           @Value("${amqp.exchange.name}") final String exchangeName
    ){
        // create exchange object
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)
                .build();
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new  Jackson2JsonMessageConverter();
    }
}
