package org.cards.mono.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.clients.AutomaClient;
import org.cards.mono.clients.DeckClient;
import org.cards.mono.eventdriven.ResolveEventPublisher;
import org.cards.mono.model.Card;
import org.cards.mono.model.Round;
import org.cards.mono.model.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonoServicesImpl implements MonoServices {

    //private final CardServiceImpl cardService;
    private final DeckClient deckClient;
    private final AutomaClient automaClient;

    private final ResolveEventPublisher resolveEventPublisher;
    private final RoundRepository roundRepository;

    @Override
    public Round getNewRound() {
        Round round = new Round();

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

        Round readyRound = roundRepository.save(round);

        return readyRound ;

    }

    @Override
    public Round getRound(int id) {
        Long roundId = Long.valueOf(id);
        Round round = roundRepository.getRoundById(roundId);

        return round;
    }

    @Override
    public Round playRound(Round round) {

        Round fullRound = automaClient.automaPlay(round);

        //resolveEventPublisher.publishRoundEventString("Hei Rabbit");
        // I  want to send the Round object
        log.info("Playing round {}", fullRound.getId());
        resolveEventPublisher.publishRoundEventObject(round);
        return fullRound;
    }
}
