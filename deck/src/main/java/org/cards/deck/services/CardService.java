package org.cards.deck.services;


import org.cards.deck.model.Card;

import java.util.HashMap;

public interface CardService {
    HashMap<Long, Card> getCards(int number);
}
