package org.cards.resolver.services;

import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.model.Card;
import org.cards.resolver.model.ResolverRound;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResolverServiceImplAdvanced implements ResolverService {

    @Override
    public String resolveRound(ResolverRound round) {
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
        String outcome = "loss";

        if(p.getSuite() == t.getSuite()
        && a.getSuite() == t.getSuite()) {
            // both match
            if(p.getValue() >= a.getValue()) {
                points = pBid.getValue() - aBid.getValue();
                outcome = "win";
            }
        }
        else if (p.getSuite() == t.getSuite()
        && a.getSuite() != t.getSuite()) {
            // only player matches
            points = pBid.getValue() + aBid.getValue();
            outcome = "win";
        }
        else if (a.getSuite() == t.getSuite()
        && p.getSuite() != t.getSuite()){
            points = - pBid.getValue() - aBid.getValue();
        }
        else if(p.getValue() >= a.getValue()) {
            points = p.getValue() - aBid.getValue();
            outcome = "win";
        }
        else points = - p.getValue() - aBid.getValue();


        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Round {} -> Outcome {}; points {}", round.getId(), outcome, points);
        return outcome + "; Points: " + points;
    }
}
