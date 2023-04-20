package main.sk.pavlovsky.sokoban.object.levelActor;

public class Player extends LevelActor {
    private int steps =0;

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
