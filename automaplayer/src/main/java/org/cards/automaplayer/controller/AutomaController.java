package org.cards.automaplayer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.automaplayer.dtos.RoundDTO;
import org.cards.automaplayer.services.AutomaService;
import org.cards.automaplayer.services.AutomaServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/automa")
public class AutomaController {

    private final AutomaServiceImpl automaService;

    @PostMapping("/playCards")
    public RoundDTO playAutoma(
            @RequestBody RoundDTO roundDTO
    ){
        RoundDTO response = automaService.playAutoma(roundDTO);

        //System.out.println(response);

        log.info("Processed Round: {} -> {}", roundDTO.getId(), LocalDateTime.now());
        return response;
    }
}
