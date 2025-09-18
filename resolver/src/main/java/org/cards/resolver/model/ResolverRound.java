package org.cards.resolver.model;

import jakarta.persistence.*;

import java.util.HashMap;

@Entity
public class ResolverRound {

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @CollectionTable(name = "player_cards", joinColumns = @JoinColumn(name = "round_id"))
    @MapKeyColumn(name = "player_position")
    @Column(name = "card_value")
    private HashMap<Long, Card> playerCards;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @CollectionTable(name = "automa_cards", joinColumns = @JoinColumn(name = "round_id"))
    @MapKeyColumn(name = "automa_position")
    @Column(name = "card_value")
    private HashMap<Long, Card> automaCards;

    @OneToOne
    private Card playerCard;
    @OneToOne
    private Card automaCard;

    @OneToOne
    private Card playerBid;
    @OneToOne
    private Card automaBid;

    @OneToOne
    private Card topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<Long, Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(HashMap<Long, Card> playerCards) {
        this.playerCards = playerCards;
    }

    public HashMap<Long, Card> getAutomaCards() {
        return automaCards;
    }

    public void setAutomaCards(HashMap<Long, Card> automaCards) {
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