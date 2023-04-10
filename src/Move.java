import java.util.logging.Level;

public class Move {
    public static void moveRight(LevelObject[][] map, int manPos1, int manPos2) {
        if (map[manPos1][manPos2 + 1] instanceof LevelObject.Box
                && map[manPos1][manPos2 + 2] instanceof LevelObject.Empty) {
            map[manPos1][manPos2 + 1] = new LevelObject.Man();
            map[manPos1][manPos2 + 2] = new LevelObject.Box();
            map[manPos1][manPos2] = new LevelObject.Empty();
        } else if (map[manPos1][manPos2 + 1] instanceof LevelObject.Box
                && map[manPos1][manPos2 + 2] instanceof LevelObject.Goal) {
            map[manPos1][manPos2 + 1] = new LevelObject.Man();
            map[manPos1][manPos2] = new LevelObject.Empty();
            map[manPos1][manPos2 + 2] = new LevelObject.ReachGoal();
        } else if (map[manPos1][manPos2+1] instanceof LevelObject.Wall){
            System.out.println("you cannot move, before you is wall");
        } else if (map[manPos1][manPos2+1] instanceof LevelObject.Box && map[manPos1][manPos2+1] instanceof LevelObject.Wall) {
            System.out.println("you cannot move, wall is after box");
        }

    }
    public static void moveLeft(LevelObject[][] map, int manPos1, int manPos2) {
        if (map[manPos1][manPos2 -1] instanceof LevelObject.Box
                && map[manPos1][manPos2 - 2] instanceof LevelObject.Empty) {
            map[manPos1][manPos2 - 1] = new LevelObject.Man();
            map[manPos1][manPos2 - 2] = new LevelObject.Box();
            map[manPos1][manPos2] = new LevelObject.Empty();
        } else if (map[manPos1][manPos2 - 1] instanceof LevelObject.Box
                && map[manPos1][manPos2 - 2] instanceof LevelObject.Goal) {
            map[manPos1][manPos2 - 1] = new LevelObject.Man();
            map[manPos1][manPos2] = new LevelObject.Empty();
            map[manPos1][manPos2 - 2] = new LevelObject.ReachGoal();
        } else if (map[manPos1][manPos2-1] instanceof LevelObject.Wall){
            System.out.println("you cannot move, before you is wall");
        } else if (map[manPos1][manPos2-1] instanceof LevelObject.Box && map[manPos1][manPos2-1] instanceof LevelObject.Wall) {
            System.out.println("you cannot move, wall is after box");
    }

    }
    public static void moveUp(LevelObject[][] map, int manPos1, int manPos2) {
        if (map[manPos1-1][manPos2] instanceof LevelObject.Box
                && map[manPos1-2][manPos2] instanceof LevelObject.Empty) {
            map[manPos1-1][manPos2] = new LevelObject.Man();
            map[manPos1-2][manPos2] = new LevelObject.Box();
            map[manPos1][manPos2] = new LevelObject.Empty();
        } else if (map[manPos1-1][manPos2] instanceof LevelObject.Box
                && map[manPos1-2][manPos2] instanceof LevelObject.Goal) {
            map[manPos1-1][manPos2] = new LevelObject.Man();
            map[manPos1][manPos2] = new LevelObject.Empty();
            map[manPos1-2][manPos2] = new LevelObject.ReachGoal();
        } else if (map[manPos1-1][manPos2] instanceof LevelObject.Wall){
            System.out.println("you cannot move, before you is wall");
        } else if (map[manPos1-1][manPos2] instanceof LevelObject.Box && map[manPos1-1][manPos2] instanceof LevelObject.Wall) {
            System.out.println("you cannot move, wall is after box");
}
    }
    public static void moveDown(LevelObject[][] map, int manPos1, int manPos2) {
        if (map[manPos1+1][manPos2] instanceof LevelObject.Box
                && map[manPos1+2][manPos2] instanceof LevelObject.Empty) {
            map[manPos1+1][manPos2] = new LevelObject.Man();
            map[manPos1+2][manPos2] = new LevelObject.Box();
            map[manPos1][manPos2] = new LevelObject.Empty();
        } else if (map[manPos1+1][manPos2] instanceof LevelObject.Box
                && map[manPos1+2][manPos2] instanceof LevelObject.Goal) {
            map[manPos1+1][manPos2] = new LevelObject.Man();
            map[manPos1][manPos2] = new LevelObject.Empty();
            map[manPos1+2][manPos2] = new LevelObject.ReachGoal();
        } else if (map[manPos1+1][manPos2] instanceof LevelObject.Wall){
            System.out.println("you cannot move, before you is wall");
        } else if (map[manPos1+1][manPos2] instanceof LevelObject.Box && map[manPos1+1][manPos2] instanceof LevelObject.Wall) {
            System.out.println("you cannot move, wall is after box");
}
    }
}
