package main.sk.pavlovsky.sokoban.render;

import main.sk.pavlovsky.sokoban.Game;
import main.sk.pavlovsky.sokoban.Inputs.View;
import main.sk.pavlovsky.sokoban.object.levelActor.Player;
import main.sk.pavlovsky.sokoban.object.levelObject.*;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static main.sk.pavlovsky.sokoban.Inputs.View.*;
import static main.sk.pavlovsky.sokoban.render.TextureFactory.*;
import static main.sk.pavlovsky.sokoban.render.TextureFactory.GOAL;

public class SwingRenderer implements Renderer{
    private JFrame frame;
    private Canvas canvas;


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
        Player player = map.getPlayer();
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                renderTexture(gr, getTexture(map.getLevelObject(x,y),size),x,y,size,xOff,yOff);
            }
        }
        for (Box b:map.getBoxes()){
            LevelObject levelObject = map.getLevelObject(b.getX(),b.getY());
            if (!game.isMoveBox()) {
                if (levelObject instanceof Goal) {
                    renderTexture(gr, BOX_ON_GOAL, b.getX(), b.getY(), size, xOff, yOff);
                } else renderTexture(gr, BOX, b.getX(), b.getY(), size, xOff, yOff);
            }else { if(findBox(game.getView(), b.getX(), b.getY(), map.getPlayer())||b.getX()== player.getXBOff()&&b.getY()== player.getYBOff()) {
                            renderNonStaticTexture(game,gr,BOX, size, b.getX(), b.getY(), game.getView());
                        }else renderTexture(gr, BOX, b.getX(), b.getY(), size, xOff, yOff);
            }}
        renderNonStaticTexture(game,gr,player.getTexture(), size, player.getX(), player.getY(), game.getView());
        g.drawImage(bi,0,0,canvas);
    }
    public boolean findBox(View view, int bx, int by, Player player){
        if (view==DOWNN && by==player.getY()+1 && bx==player.getX()){
            player.setXBOff(bx);
            player.setYBOff(by);
            return true;
        } else if (view == LEFTT && by == player.getY() && bx == player.getX() -1) {
            player.setXBOff(bx);
            player.setYBOff(by);
            return true;
        }else if (view == RIGHTT && by == player.getY() && bx == player.getX() +1) {
            player.setXBOff(bx);
            player.setYBOff(by);
            return true;
        }else if (view == UPP && by == player.getY() - 1 && bx == player.getX()) {
            player.setXBOff(bx);
            player.setYBOff(by);
        return true;
        }return false;
    }

    @Override
    public void clear() {
    }
    public JFrame getFrame() {
        return frame;
    }
    private void renderNonStaticTexture(Game game, Graphics2D gr, BufferedImage image, int size, int x, int y, View view){
        if (!game.isMovePlayer()) {

        }else if (view== LEFTT) {
            x++;
        }else if (view== RIGHTT) {
            x--;
        }else if (view==DOWNN) {
            y--;
        }else if (view== UPP) {
            y++;
        }gr.drawImage(image,(x*size)+ game.getActiveMap().getPlayer().getXOff(),(y*size)+ game.getActiveMap().getPlayer().getYOff(),canvas);
    }

    private void renderTexture(Graphics2D gr, BufferedImage image, int x, int y, int size, int xOff, int yOff) {
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
