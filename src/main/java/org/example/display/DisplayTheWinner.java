package org.example.display;

import org.example.service.ConsoleService;

public class DisplayTheWinner {
    private final  ConsoleService consoleService;

    public DisplayTheWinner(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public void print(final char winner) {

        if (winner == 'X') {
            consoleService.print("Gratulálok nyertél!");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else if (winner == 'O') {
            consoleService.print("Sajnos vesztettél!");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            consoleService.print("Döntetlen!");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
