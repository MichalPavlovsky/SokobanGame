package main.sk.pavlovsky.sokoban.render;

import main.sk.pavlovsky.sokoban.Game;
import main.sk.pavlovsky.sokoban.object.levelActor.Player;
import main.sk.pavlovsky.sokoban.object.levelObject.*;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        BufferedImage bi = new BufferedImage(640,640,BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = (Graphics2D) bi.getGraphics();
        Map map = game.getActiveMap();
        int size = 32;
        int xOff= 0;
        int yOff= 0;
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                renderObject(gr, map, getColor(map.getLevelObject(x,y)), x,y,size,xOff,yOff);
            }
        }
        for (Box b:map.getBoxes()){
            renderObject(gr, map, Color.ORANGE, b.getX(),b.getY(),size,xOff,yOff);
        }
        Player player = map.getPlayer();
        renderObject(gr, map, Color.WHITE, player.getX(), player.getY(), size,xOff,yOff);
        g.drawImage(bi,0,0,canvas);
    }
    private void renderObject(Graphics2D gr, Map map, Color color, int x, int y, int size, int xOff, int yOff){
        gr.setColor(color);
        gr.fillRect(x*size+xOff, y*size+yOff,size,size);
        LevelObject levelObject = map.getLevelObject(x,y);
        if (levelObject instanceof Goal) {
            gr.setColor(color);
            gr.fillRect(x*size+xOff+5,y*size+yOff+5, size - 10 ,size -10);
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

    public JFrame getFrame() {
        return frame;
    }
}
