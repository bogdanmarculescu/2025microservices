package org.cards.mono.eventdriven;

import lombok.Value;
import org.cards.mono.model.Card;

import java.util.HashMap;

@Value
public class ResolverEvent {

    private Long id;
    private HashMap<Long, Card> playerCards;
    private HashMap<Long, Card> automaCards;

    private Card playerCard;
    private Card automaCard;

    private Card playerBid;
    private Card automaBid;

    private Card topic;

}
