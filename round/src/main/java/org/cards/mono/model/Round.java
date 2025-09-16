package org.cards.mono.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Round {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @CollectionTable(name = "player_cards", joinColumns = @JoinColumn(name = "round_id"))
    @MapKeyColumn(name = "player_position")
    @Column(name = "card_value")
    private Map<Long, Card> playerCards = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @CollectionTable(name = "automa_cards", joinColumns = @JoinColumn(name = "round_id"))
    @MapKeyColumn(name = "automa_position")
    @Column(name = "card_value")
    private Map<Long, Card> automaCards =  new HashMap<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Card playerCard;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Card automaCard;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Card playerBid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Card automaBid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Card topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(Map<Long, Card> playerCards) {
        this.playerCards = playerCards;
    }

    public Map<Long, Card> getAutomaCards() {
        return automaCards;
    }

    public void setAutomaCards(Map<Long, Card> automaCards) {
        this.automaCards = automaCards;
    }

    public Card getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(Card playerCard) {
        this.playerCard = playerCard;
    }

    public Card getAutomaCard() {
        return automaCard;
    }

    public void setAutomaCard(Card automaCard) {
        this.automaCard = automaCard;
    }

    public Card getPlayerBid() {
        return playerBid;
    }

    public void setPlayerBid(Card playerBid) {
        this.playerBid = playerBid;
    }

    public Card getAutomaBid() {
        return automaBid;
    }

    public void setAutomaBid(Card automaBid) {
        this.automaBid = automaBid;
    }

    public Card getTopic() {
        return topic;
    }

    public void setTopic(Card topic) {
        this.topic = topic;
    }
}
