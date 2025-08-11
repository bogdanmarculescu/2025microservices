package org.cards.mono.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Round;
import org.cards.mono.services.MonoServicesImpl;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mono")
public class MonoController {
    private final MonoServicesImpl monoService;

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
}
