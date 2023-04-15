package main.sk.pavlovsky.sokoban.object.levelObject;


import main.sk.pavlovsky.sokoban.utils.MapFactory;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;
import main.sk.pavlovsky.sokoban.object.levelActor.Player;

import java.util.ArrayList;

public class Map {

    private int width;
    private int height;
    private LevelObject[][] map;
    private ArrayList<Box> boxes;
    private Player player;

    public Map(int width, int height) {
        this.height=height;
        this.width=width;
        this.map= new LevelObject[height][width];
        this.boxes=new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        MapFactory factory = new MapFactory();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (x == getPlayer().getX() && y == getPlayer().getY()) {
                    if (getLevelObject(x, y) instanceof Goal) {
                        sb.append('+');
                    } else sb.append("@");
                } else if (isBox(x, y)) {
                    if (getLevelObject(x, y) instanceof Empty) sb.append("o");
                    else if (getLevelObject(x, y) instanceof Goal) sb.append("*");
                } else sb.append(factory.toMap(getLevelObject(x, y)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    private boolean isBox(int x, int y) {
        return this.getBoxes().stream().anyMatch(it -> it.getX()==x && it.getY() ==y);
    }

    public LevelObject getLevelObject(int x, int y) {
        return this.map[y][x];
    }

    public void setMapObject(int x, int y, LevelObject obj){
        this.map[y][x]=obj;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void createPlayer(int x, int y) {
        this.player=new Player(x, y);
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }
    public void createBox(int x, int y){
        this.boxes.add(new Box(x,y));
    }
}
