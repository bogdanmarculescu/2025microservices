package org.cards.deck.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cards.deck.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);
    private final HashMap<Long, Card> deck = new HashMap<>();

    public CardServiceImpl(){
        ObjectMapper mapper = new ObjectMapper();
        //File jsonFile = new File("Mono/src/main/resources/cards.json");

        try (InputStream inputStream = new ClassPathResource("cards.json").getInputStream()){
            List<Card> readDeck = mapper.readValue(inputStream, new TypeReference<List<Card>>() {});
            Long id = 1L;
            for(Card card: readDeck) {
                System.out.println("Key" + id + ": " + card);
                deck.put(id, card);
                id++;
            }
        }
        catch(IOException e){
           e.printStackTrace();
        }

    }

    @Override
    public HashMap<Long, Card> getCards(int number) {
        List<Card> shuffled = new ArrayList<>(deck.values());
        Collections.shuffle(shuffled);

        //System.out.println("Deck Service: Shuffled cards!" + LocalDate.now().toString());

        log.info("Shuffled:  {}; ", LocalDateTime.now().toString());

        HashMap<Long, Card> result = new HashMap<>(number);

        for(int i = 1; i <= number; i++){
            result.put(Long.valueOf(i), shuffled.get(i));
        }
        return result;
    }
}
