package main.sk.pavlovsky.sokoban;

public class LevelActor {
    private int x,y;

    public LevelActor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
    }
}
