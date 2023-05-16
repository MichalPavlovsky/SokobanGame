package main.sk.pavlovsky.sokoban.render;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureFactory {
    public static BufferedImage WALL;
    public static BufferedImage GOAL;
    public static BufferedImage BOX;
    public static BufferedImage PLAYER;
    public static BufferedImage BOX_ON_GOAL;
    static {
        WALL= TextureFactory.load("wall.png");
        GOAL= TextureFactory.load("goal.png");
        BOX= TextureFactory.load("box.png");
        PLAYER= TextureFactory.load("DOWN0.png");
        BOX_ON_GOAL= TextureFactory.load("boxOnGoal.png");
    }
    private static BufferedImage load(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Program Files\\Projekty\\Sokoban\\src\\main\\sprites\\"+path));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
