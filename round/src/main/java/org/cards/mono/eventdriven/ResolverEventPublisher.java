package org.cards.mono.eventdriven;

import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Round;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResolverEventPublisher {

    // config stuff
    // what exchange
    // what is the message

    private final AmqpTemplate amqpTemplate;
    private final String exchangeName;

    public ResolverEventPublisher(
            final AmqpTemplate amqpTemplate,
            @Value("${amqp.exchange.name}") final String exchangeName
    ) {
        this.amqpTemplate = amqpTemplate;
        this.exchangeName = exchangeName;
    }

    public void publishResolverEvent(
            Round round
    ) {
        //build the message/even
        String event = buildEventString(round);

        //decide on routing
        String routingKey= "round.solved";
        // send the message

        amqpTemplate.convertAndSend(exchangeName, routingKey, event);
    }

    private String buildEventString(Round round) {
        StringBuffer sb = new StringBuffer();

        sb.append("{")
                .append("\roundId:" + round.getId())
                .append("}");

        return sb.toString();
    }
}
