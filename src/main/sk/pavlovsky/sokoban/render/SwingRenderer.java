package main.sk.pavlovsky.sokoban.render;

import main.sk.pavlovsky.sokoban.Game;
import main.sk.pavlovsky.sokoban.object.levelActor.Player;
import main.sk.pavlovsky.sokoban.object.levelObject.*;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static main.sk.pavlovsky.sokoban.render.TextureFactory.*;
import static main.sk.pavlovsky.sokoban.render.TextureFactory.GOAL;

public class SwingRenderer implements Renderer{
    private JFrame frame;
    private Canvas canvas;
    private int xOff=0;
    private int yOff = 0;
    public int getXOff() {
        return xOff;
    }
    public void setXOff(int xOff) {
        this.xOff = xOff+ getXOff();
    }
    public void setXOff2(int xOff) {
        this.xOff = xOff;
    }
    public int getYOff() {
        return yOff;
    }
    public void setYOff(int yOff) {
        this.yOff = yOff+ getYOff();
    }
    public void setyOff2(int yOff2) {
        this.yOff = yOff2;
    }

    private Graphics2D g;
    public SwingRenderer() {
        frame = new JFrame();
        frame.setSize(1280, 1280);
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
        frame.dispose();
    }
    @Override
    public void render(Game game) {
        BufferedImage bi = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = (Graphics2D) bi.getGraphics();
        Map map = game.getActiveMap();
        int size = 64;
        int xOff= 0;
        int yOff= 0;
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                renderTexture(gr, map, getTexture(map.getLevelObject(x,y),size),x,y,size,xOff,yOff);
            }
        }
        for (Box b:map.getBoxes()){
            LevelObject levelObject = map.getLevelObject(b.getX(),b.getY());
            if (levelObject instanceof Goal){
                renderTexture(gr, map, BOX_ON_GOAL, b.getX(), b.getY(),size,xOff,yOff);
            }else renderTexture(gr,map, BOX,b.getX(),b.getY(),size,xOff,yOff);
        }
        Player player = map.getPlayer();
        renderNonStaticTexture(game,gr,map,player.getTexture(), size, player.getX(), player.getY());
        g.drawImage(bi,0,0,canvas);
    }

    @Override
    public void clear() {
    }
    public JFrame getFrame() {
        return frame;
    }
    private void renderNonStaticTexture(Game game,Graphics2D gr, Map map, BufferedImage image, int size,int x, int y){
        moveOff(image, game);
        if (!game.isMoveRender()) {
            setXOff2(0);
            setyOff2(0);
        }else if (image==LEFT0) {
            x++;
        }else if (image==RIGHT0) {
            x--;
        }else if (image==DOWN0) {
            y--;
        }else if (image==UP0) {
            y++;
        }gr.drawImage(image,(x*size)+ getXOff(),(y*size)+ getYOff(),canvas);
    }
    private void moveOff(BufferedImage image, Game game) {
        if (image==LEFT0 ) {
            if (getXOff()>-64) {
                setXOff(-4);
            }else game.setMoveRender(false);
        }else if (image==RIGHT0) {
            if (getXOff()<64) {
                setXOff(4);
            }else game.setMoveRender(false);
        }else if (image==DOWN0) {
            if (getYOff()<64) {
                setYOff(4);
            }else game.setMoveRender(false);
        }else if (image==UP0) {
            if (getYOff()>-64) {
                setYOff(-4);
            }else game.setMoveRender(false);
        }
    }


    private void renderTexture(Graphics2D gr, Map map, BufferedImage image, int x, int y, int size, int xOff, int yOff) {
        gr.drawImage(image,(x*size)+xOff, (y*size)+yOff,canvas);
    }
    private BufferedImage getTexture(LevelObject levelObject, int size) {
        if (levelObject instanceof Empty)  {
            return new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        }else if (levelObject instanceof Goal) {
            return GOAL;
        }else if (levelObject instanceof Wall) {
            return WALL;
        }
        throw new RuntimeException("There is no such Level Object defined");
    }
}
