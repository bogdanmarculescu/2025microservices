package org.cards.mono.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Round;
import org.cards.mono.services.RoundServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/round")
public class RoundController {
    private final RoundServices monoService;

    @GetMapping
    public Round getNewRound() {
        // get a completely new round
        return monoService.getNewRound();
    }

    @GetMapping("/{id}")
    public String getRound(@PathVariable String id) {
        // get round by id
        return "";
    }

    @PostMapping
    public String playRound(
            @RequestBody String round){
        // post round play (card and bit)
        return "";
    }

    @PostMapping("/submitMove")
    public ResponseEntity<String> submitMove(
            @RequestBody Round round){
        // post round play (card and bit)

        System.out.println("Round: " + round.toString());

        // Add automa card play
        Round automaPlay = monoService.playRound(round);
        round.setAutomaCard(automaPlay.getAutomaCard());
        round.setAutomaBid(automaPlay.getAutomaBid());

        // Resolve outcome.

        System.out.println("P: "
                + round.getPlayerCard().getFilename()
                + " - vs - A:"
                + round.getAutomaCard().getFilename());

        return ResponseEntity.ok("Submitted");
    }
}
