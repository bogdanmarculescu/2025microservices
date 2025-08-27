package org.cards.deck.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.deck.model.Card;
import org.cards.deck.services.CardServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deck")
public class DeckController {
    private final CardServiceImpl cardService;

    @GetMapping
    public HashMap<Long, Card> getResult() {
        //TODO: stop hardcoding stuff, for crying out loud
        return cardService.getCards(7);
    }

}
