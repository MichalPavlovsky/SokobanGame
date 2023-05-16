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
    public static BufferedImage DOWN0;
    public static BufferedImage DOWN1;
    public static BufferedImage DOWN2;
    public static BufferedImage LEFT0;
    public static BufferedImage LEFT1;
    public static BufferedImage LEFT2;
    public static BufferedImage RIGHT0;
    public static BufferedImage RIGHT1;
    public static BufferedImage RIGHT2;
    public static BufferedImage UP0;
    public static BufferedImage UP1;
    public static BufferedImage UP2;
    public static BufferedImage BOX_ON_GOAL;
    static {
        WALL= TextureFactory.load("wall.png");
        GOAL= TextureFactory.load("goal.png");
        BOX= TextureFactory.load("box.png");
        PLAYER= TextureFactory.load("DOWN0.png");
        BOX_ON_GOAL= TextureFactory.load("boxOnGoal.png");
        UP0= TextureFactory.load("UP0.png");
        UP1= TextureFactory.load("UP1.png");
        UP2= TextureFactory.load("UP2.png");
        DOWN0= TextureFactory.load("DOWN0.png");
        DOWN1= TextureFactory.load("DOWN1.png");
        DOWN2= TextureFactory.load("DOWN2.png");
        RIGHT0= TextureFactory.load("RIGHT0.png");
        RIGHT1= TextureFactory.load("RIGHT1.png");
        RIGHT2= TextureFactory.load("RIGHT2.png");
        LEFT0= TextureFactory.load("LEFT0.png");
        LEFT1= TextureFactory.load("LEFT1.png");
        LEFT2= TextureFactory.load("LEFT2.png");

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
