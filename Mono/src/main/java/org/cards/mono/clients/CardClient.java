package org.cards.mono.clients;

import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@Service
public class CardClient {

    // GET /api/cards
    // -> HashMap<Long, Card>
    private final String restServiceUrl;
    private final RestTemplate restTemplate;

    public CardClient(
            RestTemplateBuilder restTemplateBuilder
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceUrl = "http://localhost:8001/api/cards";
    }

    public HashMap<Long, Card> getCards(int numberOfCards){

        log.debug("Client sending request");
        System.out.println("Client sending request");
        String url = this.restServiceUrl + "/newRound";
        ResponseEntity<HashMap<Long, Card>> response = null;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<HashMap<Long, Card>>(){}
            );
        }
        catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        System.out.println("Response: " + response.getBody());
        return response.getBody();
    }

}
