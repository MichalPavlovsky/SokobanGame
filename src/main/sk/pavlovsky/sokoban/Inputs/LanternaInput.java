package main.sk.pavlovsky.sokoban.Inputs;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;


import java.io.IOException;

import static main.sk.pavlovsky.sokoban.Inputs.Direction.*;

public class LanternaInput implements Inputter{

    Screen screen;

    public LanternaInput(Screen screen) {
        this.screen = screen;
    }

    public Direction getInput(){
        try {KeyStroke stroke = screen.readInput();
            if (stroke == null || stroke.getCharacter() == null) return NONE;
            switch (stroke.getCharacter()) {
                case 'n': return NEXT;
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
