package org.example.Display;

import org.example.service.ConsoleService;

public class DisplayTheWinner {
    final ConsoleService consoleService;

    public DisplayTheWinner(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public void print(char winner){

        if(winner=='X'){
            consoleService.print("Gratulálok nyertél!");
        }else if(winner=='O'){
            consoleService.print("Sajnos vesztettél!");
        }else{
            consoleService.print("Döntetlen!");
        }
    }
}
