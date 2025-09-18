package org.cards.resolver.model;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ResolverRoundRepository extends JpaRepository<ResolverRound, Long> {
    ResolverRound  getRoundById(Long id);
}
