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

    @Override
    public void move(int dx, int dy) {

        super.move(dx, dy);
    }

    public Player(int x, int y) {

        super(x, y);
    }

}
