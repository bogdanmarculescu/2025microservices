package org.cards.mono.eventdriven;

import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Round;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResolveEventPublisher {
    // configuration
    private final String exchangeName;
    private final AmqpTemplate amqpTemplate;

    public ResolveEventPublisher(
            final AmqpTemplate amqpTemplate,
            @Value("${amqp.exchange.name}") final String exchangeName
    ){
        this.amqpTemplate = amqpTemplate;
        this.exchangeName = exchangeName;
    }
    // where ?

    public void publishRoundEventString(String message){
        String routingKey = "rounds.complete";

        //boolean won = true;

       //if(won) routingKey = "round.event.won";
        //else routingKey = "round.event.lost";

        log.info("Sending message to Rabbit:" + message);
        amqpTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    public void publishRoundEventObject(Round round){
        String routingKey = "rounds.complete";

        StringBuffer result = new StringBuffer();
        result.append("{")
                .append("\"roundId\":\"" + round.getId())
                .append("}");

        log.info("Sending message to Rabbit:" + round);
        amqpTemplate.convertAndSend(exchangeName, routingKey, round);
    }

}
