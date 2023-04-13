package main.sk.pavlovsky.sokoban;

import main.sk.pavlovsky.sokoban.levelObject.Empty;
import main.sk.pavlovsky.sokoban.levelObject.Goal;
import main.sk.pavlovsky.sokoban.levelObject.ReachGoal;
import main.sk.pavlovsky.sokoban.levelObject.Wall;

import java.util.logging.Level;

public class MapFactory {
    public LevelObject fromInputFile(char c) {
        switch (c) {
            case '#' : return new Wall();
            case ';' : return new ReachGoal();
            case '.' :
            case '*' : return new Goal();
            default: return new Empty();

}
    }
    public char toMap(LevelObject map) {
        if(map instanceof Wall) return '#';
        if(map instanceof Goal) return '.';
        return ' ';
    }
}
