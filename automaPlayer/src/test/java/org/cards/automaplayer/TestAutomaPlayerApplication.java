package org.cards.automaplayer;

import org.springframework.boot.SpringApplication;

public class TestAutomaPlayerApplication {

    public static void main(String[] args) {
        SpringApplication.from(AutomaPlayerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
