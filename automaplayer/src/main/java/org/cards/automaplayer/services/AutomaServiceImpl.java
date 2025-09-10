package org.cards.automaplayer.services;

import org.cards.automaplayer.dtos.RoundDTO;
import org.cards.automaplayer.model.Card;
import org.springframework.stereotype.Service;

@Service
public class AutomaServiceImpl implements AutomaService {



    @Override
    public RoundDTO playAutoma(RoundDTO roundDTO) {
        // Start with dummy default play

        Card playedCard = roundDTO.getAutomaCards().get(1L);
        Card bidCard = roundDTO.getAutomaCards().get(2L);

        System.out.println("playedCard = " + playedCard);
        System.out.println("bidCard = " + bidCard);

        roundDTO.setAutomaCard(playedCard);
        roundDTO.setAutomaBid(bidCard);

        System.out.println(roundDTO);

        return roundDTO;
    }
}
