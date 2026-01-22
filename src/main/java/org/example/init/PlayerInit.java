package org.example.init;

import org.example.domain.BotPlayer;
import org.example.domain.HumanPlayer;
import org.example.service.ConsoleService;

public class PlayerInit {
    private final ConsoleService consoleService;

    public PlayerInit(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public HumanPlayer readHumanPlayer() {
        return new HumanPlayer(consoleService.readStringFromConsole("Please provide your name: "));
    }

    public BotPlayer prepareBotPlayer() {
        return new BotPlayer();
    }
}
