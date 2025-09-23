package org.cards.resolver.model;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ResolverRoundRepository extends JpaRepository<Round, Long> {
    Round getRoundById(Long id);
}
