package org.cards.resolver.eventdriven;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.model.ResolverRound;
import org.cards.resolver.services.ResolverServiceImpl;
import org.cards.resolver.services.ResolverServiceImplAdvanced;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResolverHandler {
    private final ResolverServiceImplAdvanced resolverService;

    @RabbitListener(queues =  "rounds.complete")
    public void handleEvent(
            ResolverRound round
    ){
        log.info("Received message: {}", round);
        String outcome  = resolverService.resolveRound(round);
        log.info("Resolved round: {}", outcome);
    }
}
