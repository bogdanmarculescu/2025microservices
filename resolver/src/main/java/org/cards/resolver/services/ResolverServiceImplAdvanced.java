package org.cards.resolver.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.model.Card;
import org.cards.resolver.model.CardRepository;
import org.cards.resolver.model.Round;
import org.cards.resolver.model.ResolverRoundRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResolverServiceImplAdvanced implements ResolverService {

    private final ResolverRoundRepository roundRepository;
    private final CardRepository cardRepository;

    @Override
    public String resolveRound(Round round) {
        Card p = round.getPlayerCard();
        Card a = round.getAutomaCard();
        Card t = round.getTopic();

        Card pBid = round.getPlayerBid();
        Card aBid = round.getAutomaBid();

        // if both cards match the topic suite, largest wins

        //  if only one card matches suite, it wins

        // if neither matches, largest wins

        // points are awarded based on the highest bid

        int points = 0;
        String outcome = "Automa";

        if(p.getSuite() == t.getSuite()
        && a.getSuite() == t.getSuite()) {
            // both match
            if(p.getValue() >= a.getValue()) {
                points = pBid.getValue() - aBid.getValue();
                outcome = "Player";
            }
        }
        else if (p.getSuite() == t.getSuite()
        && a.getSuite() != t.getSuite()) {
            // only player matches
            points = pBid.getValue() + aBid.getValue();
            outcome = "Player";
        }
        else if (a.getSuite() == t.getSuite()
        && p.getSuite() != t.getSuite()){
            points = - pBid.getValue() - aBid.getValue();
        }
        else if(p.getValue() >= a.getValue()) {
            points = p.getValue() - aBid.getValue();
            outcome = "Player";
        }
        else points = - p.getValue() - aBid.getValue();


        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        round.setWinner(outcome);
        round.setPoints(points);

        log.info("RoundId: {};", round.getId() );

        //roundRepository.save(round);
        saveRound(round);

        log.info("Round {} -> Outcome {}; points {}", round.getId(), outcome, points);
        return outcome + "; Points: " + points;

    }

    @Override
    public Round getRoundById(Long id) {
        return roundRepository.getRoundById(id);
    }

    public List<Round> getRounds() {
        return roundRepository.findAll();
    }


    @Transactional
    public Round saveRound(Round incomingRound) {
        incomingRound.setPlayerCard(resolveCard(incomingRound.getPlayerCard()));
        incomingRound.setAutomaCard(resolveCard(incomingRound.getAutomaCard()));

        incomingRound.setPlayerBid(resolveCard(incomingRound.getPlayerBid()));
        incomingRound.setAutomaBid(resolveCard(incomingRound.getAutomaBid()));

        incomingRound.setTopic(resolveCard(incomingRound.getTopic()));

        incomingRound.setPlayerCards(resolveCards(incomingRound.getPlayerCards()));
        incomingRound.setAutomaCards(resolveCards(incomingRound.getAutomaCards()));

        return roundRepository.save(incomingRound);
    }

    private Card resolveCard(Card card){
        if(card == null) return null;
        return cardRepository
                .findByExternalId(card.getExternalId())
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
