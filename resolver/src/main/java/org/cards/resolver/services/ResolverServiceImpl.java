package org.cards.resolver.services;

import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.model.Card;
import org.cards.resolver.model.Round;
import org.springframework.stereotype.Service;

@Slf4j
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
        log.debug(outcome);

        return outcome;
    }

    @Override
    public Round getRoundById(Long id) {
        return null;
    }
}
