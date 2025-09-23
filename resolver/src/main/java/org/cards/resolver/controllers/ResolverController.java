package org.cards.resolver.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.model.Round;
import org.cards.resolver.services.ResolverServiceImplAdvanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resolver")
public class ResolverController {
    private final ResolverServiceImplAdvanced resolverService;

    @GetMapping("/outcome/{id}")
    public String getOutcome(@PathVariable Long id) {
        Round outcome = resolverService.getRoundById(id);
        log.info("getOutcome {}", outcome);
        String result = "Outcome: " + outcome.getWinner() + " ; Points: " + outcome.getPoints();
        log.info(LocalDateTime.now() + " => " + outcome.getPoints());
        return result;
    }

    @GetMapping("/outcomes")
    public List<Round> getOutcomes() {
        return resolverService.getRounds();
    }
}

