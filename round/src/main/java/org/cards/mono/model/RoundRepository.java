package org.cards.mono.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {

    Round getRoundById(Long id);
}
