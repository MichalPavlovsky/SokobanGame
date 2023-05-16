package main.sk.pavlovsky.sokoban.render;

import main.sk.pavlovsky.sokoban.Game;
import main.sk.pavlovsky.sokoban.Inputs.Direction;

public interface Renderer {
    void init();
    void deinit();
    void render(Game game);
    void clear();
}
