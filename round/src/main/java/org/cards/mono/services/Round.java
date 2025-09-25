package org.cards.mono.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.clients.AutomaClient;
import org.cards.mono.clients.DeckClient;
import org.cards.mono.eventdriven.ResolveEventPublisher;
import org.cards.mono.model.Card;
import org.cards.mono.model.CardRepository;
import org.cards.mono.model.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Round implements RoundServices {

    //private final CardServiceImpl cardService;
    private final DeckClient deckClient;
    private final AutomaClient automaClient;

    private final ResolveEventPublisher resolveEventPublisher;
    private final RoundRepository roundRepository;
    private final CardRepository cardRepository;

    @Override
    public org.cards.mono.model.Round getNewRound() {
        org.cards.mono.model.Round round = new org.cards.mono.model.Round();

        HashMap<Long, Card> cards = deckClient.getCards(7);

        //3 cards to player
        round.getPlayerCards().put(Long.valueOf(1), cards.get(Long.valueOf(1)));
        round.getPlayerCards().put(Long.valueOf(2), cards.get(Long.valueOf(2)));
        round.getPlayerCards().put(Long.valueOf(3), cards.get(Long.valueOf(3)));

        // 3 cards to automa
        round.getAutomaCards().put(Long.valueOf(1), cards.get(Long.valueOf(4)));
        round.getAutomaCards().put(Long.valueOf(2), cards.get(Long.valueOf(5)));
        round.getAutomaCards().put(Long.valueOf(3), cards.get(Long.valueOf(6)));

        round.setTopic(cards.get(Long.valueOf(7)));

        //TODO: better id handling, obviously
        //round.setId(Long.valueOf(42));

        round.setPlayerCards(resolveCards(round.getPlayerCards()));
        round.setAutomaCards(resolveCards(round.getAutomaCards()));

        round.setTopic(resolveCard(round.getTopic()));

        round.setPlayerBid(resolveCard(round.getPlayerBid()));
        round.setPlayerCard(resolveCard(round.getPlayerCard()));

        round.setAutomaBid(resolveCard(round.getAutomaBid()));
        round.setAutomaCard(resolveCard(round.getAutomaCard()));

        org.cards.mono.model.Round readyRound = roundRepository.save(round);

        return readyRound ;

    }

    @Override
    public org.cards.mono.model.Round getRound(int id) {
        Long roundId = Long.valueOf(id);
        org.cards.mono.model.Round round = roundRepository.getRoundById(roundId);

        return round;
    }

    @Override
    public org.cards.mono.model.Round playRound(org.cards.mono.model.Round round) {

        org.cards.mono.model.Round fullRound = automaClient.automaPlay(round);

        //resolveEventPublisher.publishRoundEventString("Hei Rabbit");
        // I  want to send the Round object
        log.info("Playing round {}", fullRound.getId());
        resolveEventPublisher.publishRoundEventObject(round);
        return fullRound;
    }

    private Card resolveCard(Card card){
        if(card == null) return null;
        return cardRepository
                .findById(card.getId())
                .orElseGet(() -> cardRepository.save(card));
    }

    private Map<Long, Card> resolveCards(Map<Long, Card> cards){
        Map<Long, Card> result = new HashMap<>();
        for(Map.Entry<Long, Card> incomingEntry : cards.entrySet()){
            Card card = resolveCard(incomingEntry.getValue());
            result.put(incomingEntry.getKey(), card);
        }
        return result;
    }
}
