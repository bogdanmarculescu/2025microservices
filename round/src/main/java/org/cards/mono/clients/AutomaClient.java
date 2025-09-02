package org.cards.mono.clients;


import org.cards.mono.model.Card;
import org.cards.mono.model.Round;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class AutomaClient {
    public final String url;
    private final RestTemplate restTemplate;

    public AutomaClient(
            RestTemplateBuilder builder,
            @Value("${automa.client.host}") final String url
    ){
        this.url = url;
        this.restTemplate = builder.build();
    }

    public Round automaPlay(Round round){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Round> entity = new HttpEntity<>(round, headers);

        ResponseEntity<Round> response = restTemplate.exchange(
                url + "/playCards",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Round>(){}
        );

        Round responseRound = response.getBody();

        round.setAutomaCard(responseRound.getAutomaCard());
        round.setAutomaBid(responseRound.getAutomaBid());


        return round;
    }

}
