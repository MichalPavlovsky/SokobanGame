package main.sk.pavlovsky.sokoban.render;

import main.sk.pavlovsky.sokoban.Game;

public interface Renderer {
    void init();
    void deinit();
    void render(Game game);
    void clear();
}
