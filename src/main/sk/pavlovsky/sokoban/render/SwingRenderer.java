package main.sk.pavlovsky.sokoban.render;

import main.sk.pavlovsky.sokoban.Game;
import main.sk.pavlovsky.sokoban.object.levelObject.*;

import javax.swing.*;
import java.awt.*;

public class SwingRenderer implements Renderer{
    private JFrame frame;
    private Canvas canvas;
    private Graphics2D g;
    public SwingRenderer() {
        frame = new JFrame();
        frame.setSize(640, 640);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        frame.add(canvas);
    }

    @Override
    public void init() {
        g= (Graphics2D) canvas.getGraphics().create();
    }

    @Override
    public void deinit() {
        g.dispose();
    }

    @Override
    public void render(Game game) {
        Map map = game.getActiveMap();
        int size = 32;
        int xOff= 0;
        int yOff= 0;
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                g.setColor(getColor(map.getLevelObject(x,y)));
                g.fillRect(x*size+xOff, y*size+yOff,size,size);
            }
        }
    }

    @Override
    public void clear() {

    }
    private Color getColor(LevelObject levelObject) {
        if (levelObject instanceof Empty) {
            return Color.BLACK;
        }else if (levelObject instanceof Goal) {
            return Color.YELLOW;
        }else if (levelObject instanceof Wall) {
            return Color.BLUE;
        }
        return Color.RED;
}
}
