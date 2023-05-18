package main.sk.pavlovsky.sokoban.object.levelActor;

import main.sk.pavlovsky.sokoban.render.TextureFactory;

import java.awt.image.BufferedImage;

public class Player extends LevelActor {
    private int steps =0;

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    private BufferedImage texture= TextureFactory.DOWN0;

    public int getSteps() {
        return steps;
    }
    public void addSteps(int d) {
        steps+=d;
    }

    private int XBOff = -1;
    private int YBOff = -1;

    public int getXBOff() {
        return XBOff;
    }

    public void setXBOff(int XBOff) {
        this.XBOff = XBOff;
    }

    public int getYBOff() {
        return YBOff;
    }

    public void setYBOff(int YBOff) {
        this.YBOff = YBOff;
    }

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

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
    }

    public Player(int x, int y) {
        super(x, y);
    }

}
