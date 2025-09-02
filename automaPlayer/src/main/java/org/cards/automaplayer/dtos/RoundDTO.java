package org.cards.automaplayer.dtos;

import org.cards.automaplayer.model.Card;

import java.util.HashMap;

public class RoundDTO {
    private Long id;

    private HashMap<Long, Card> automaCards;
    private Card automaCard;
    private Card automaBid;

    public RoundDTO(Long id, HashMap<Long, Card> automaCards) {
        this.id = id;
        this.automaCards = automaCards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<Long, Card> getAutomaCards() {
        return automaCards;
    }

    public void setAutomaCards(HashMap<Long, Card> automaCards) {
        this.automaCards = automaCards;
    }

    public Card getAutomaCard() {
        return automaCard;
    }

    public void setAutomaCard(Card automaCard) {
        this.automaCard = automaCard;
    }

    public Card getAutomaBid() {
        return automaBid;
    }

    public void setAutomaBid(Card automaBid) {
        this.automaBid = automaBid;
    }
}
