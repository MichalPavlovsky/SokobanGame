package main.sk.pavlovsky.sokoban.Inputs;

import main.sk.pavlovsky.sokoban.render.TextureFactory;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class SwingInput implements Inputter{
    private Direction lastInput = Direction.NONE;
    public SwingInput(JFrame frame){
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'w') {
                    lastInput = Direction.UP;
                } else if (e.getKeyChar()=='a') {
                    lastInput = Direction.LEFT;
                } else if (e.getKeyChar()=='s') {
                    lastInput = Direction.DOWN;
                } else if (e.getKeyChar()=='d') {
                    lastInput = Direction.RIGHT;
                }else if (e.getKeyChar()=='q') {
                    lastInput = Direction.QUIT;
                }else if (e.getKeyChar()=='n') {
                    lastInput = Direction.NEXT;
                }
            }
        });
    }

    @Override
    public Direction getInput() {
        if (lastInput!= Direction.NONE) {
            Direction ret = lastInput;
            lastInput = Direction.NONE;
            return ret;
        }
        return Direction.NONE;
    }
}
