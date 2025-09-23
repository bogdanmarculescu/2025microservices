package org.cards.resolver.services;

import org.cards.resolver.model.Round;

public interface ResolverService {
    public String resolveRound(Round round);
    public Round getRoundById(Long id);
}
