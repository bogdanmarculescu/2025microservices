package org.cards.resolver.services;

import org.cards.resolver.model.Card;
import org.cards.resolver.model.Round;
import org.springframework.stereotype.Service;

@Service
public class ResolverServiceImpl implements ResolverService {
    @Override
    public String resolveRound(Round round) {
        //TODO: some clever way to decide

        Card p = round.getPlayerCard();
        Card a = round.getAutomaCard();
        Card t = round.getTopic();

        String outcome = "loss";

        if(p.getSuite() == t.getSuite()) {
            if(p.getValue() >= a.getValue()) {
                outcome = "win";
            }
        }

        return outcome;
    }

    @Override
    public Round getRoundById(Long id) {
        return null;
    }
}
