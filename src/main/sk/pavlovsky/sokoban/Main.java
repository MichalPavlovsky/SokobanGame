package main.sk.pavlovsky.sokoban;


import main.sk.pavlovsky.sokoban.utils.LoaderLevel;

import java.io.IOException;

public class Main {
            public static void main(String[] args) throws IOException {
                LoaderLevel level = new LoaderLevel();
                Game game = new Game(level.loadLevels("levelss.txt"));
                game.start();

            }
}