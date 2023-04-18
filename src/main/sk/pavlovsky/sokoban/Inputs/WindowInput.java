package main.sk.pavlovsky.sokoban.Inputs;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;


import java.io.IOException;

import static main.sk.pavlovsky.sokoban.Inputs.Direction.*;

public class WindowInput {
    public static Direction getInput(Screen screen){
        try {
            KeyStroke stroke = screen.readInput();
            switch (stroke.getCharacter()) {
                case 'w': return UP;
                case 's': return DOWN;
                case 'a': return LEFT;
                case 'd': return RIGHT;
                case 'q': return QUIT;
                default: return NONE;
            }
        }catch (IOException e){ //co zachytava?
            throw new RuntimeException(e);
        }
    }
}
