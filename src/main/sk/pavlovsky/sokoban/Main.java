package main.sk.pavlovsky.sokoban;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import main.sk.pavlovsky.sokoban.utils.LoaderLevel;

import java.io.IOException;

public class Main {
            public static void main(String[] args) throws IOException {
                LoaderLevel level = new LoaderLevel();
                Game game = new Game(level.loadLevels("levelss.txt"));

                Terminal terminal = new DefaultTerminalFactory().createTerminal();
                Screen screen = new TerminalScreen(terminal);

                screen.startScreen();

                game.start(screen);

                screen.stopScreen();
    }
}