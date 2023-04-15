package main.sk.pavlovsky.sokoban;

import main.sk.pavlovsky.sokoban.utils.LoaderLevel;

public class Main {
            public static void main(String[] args) {
                LoaderLevel level = new LoaderLevel();
                Game game = new Game(level.loadLevels("levelss.txt"));
                game.start();

    }
}