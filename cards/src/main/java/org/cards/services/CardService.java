package org.cards.services;


import org.cards.model.Card;

import java.util.HashMap;

public interface CardService {
    HashMap<Long, Card> getCards(int number);
}
