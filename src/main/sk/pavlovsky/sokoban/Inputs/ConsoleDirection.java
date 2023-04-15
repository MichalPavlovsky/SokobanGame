package main.sk.pavlovsky.sokoban.Inputs;

import java.io.IOException;

import static main.sk.pavlovsky.sokoban.Inputs.Direction.*;

public class ConsoleDirection {

    public static Direction getDirection(){
    try{
        byte[] b = new byte[1];
        System.in.read(b);
        switch (b[0]) {
            case 'w': return UP;
            case 's': return DOWN;
            case 'a': return LEFT;
            case 'd': return RIGHT;
            case 'q': return QUIT;
            default: return NONE;
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
}
