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
                gr.setColor(getColor(map.getLevelObject(x,y)));
                gr.fillRect(x*size+xOff, y*size+yOff,size,size);
            }
        }
        for (Box b:map.getBoxes()){
            gr.setColor(Color.ORANGE);
            gr.fillRect(b.getX()*size+xOff, b.getY()*size+yOff,size,size);
        }
        Player player = map.getPlayer();
        gr.setColor(Color.WHITE);
        gr.fillRect(player.getX()*size+xOff,player.getY()*size+yOff, size,size);
        g.drawImage(bi,0,0,canvas);
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
