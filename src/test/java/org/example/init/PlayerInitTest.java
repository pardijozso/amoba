package org.example.init;


import org.example.domain.BotPlayer;
import org.example.domain.HumanPlayer;
import org.example.service.ConsoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerInitTest {

    @Mock
    private ConsoleService consoleService;

    private PlayerInit playerInit;

    @BeforeEach
    void setUp() {
        playerInit = new PlayerInit(consoleService);
    }

    @Test
    void readHumanPlayer_readsNameFromConsole_andCreatesHumanPlayer() {
        // given
        when(consoleService.readStringFromConsole("Add meg a neved: "))
                .thenReturn("TesztElek");

        // when
        HumanPlayer player = playerInit.readHumanPlayer();

        // then
        assertNotNull(player);
        assertEquals("TesztElek", player.getName());
        verify(consoleService)
                .readStringFromConsole("Add meg a neved: ");
    }

    @Test
    void prepareBotPlayer_createsBotPlayer() {
        // when
        BotPlayer bot = playerInit.prepareBotPlayer();

        // then
        assertNotNull(bot);
        assertTrue(bot instanceof BotPlayer);
        verifyNoInteractions(consoleService);
    }
}