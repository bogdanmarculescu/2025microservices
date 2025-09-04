package org.cards.resolver.eventdriven;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.service.ResolverServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolverHandler {
    private final ResolverServiceImpl resolverService;

    @RabbitListener(queues = "round.solved")
    void handleSolveEvent(
            String message
    ){
        log.info("Solve event received: " + message);
    }
}
