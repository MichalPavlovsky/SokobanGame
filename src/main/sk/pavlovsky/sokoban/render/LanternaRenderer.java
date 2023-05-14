package main.sk.pavlovsky.sokoban.render;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import main.sk.pavlovsky.sokoban.Game;

import java.io.IOException;

public class LanternaRenderer implements Renderer{
    private Screen screen;
    public LanternaRenderer(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void init() {
        try {
            this.screen.startScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deinit() {
        try {
            this.screen.stopScreen();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Game game) {
        try {
            this.screen.refresh();
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("It is not possible to render.");
        }
    }

    @Override
    public void clear() {
        this.screen.clear();
    }
}
