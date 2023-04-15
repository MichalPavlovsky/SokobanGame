package main.sk.pavlovsky.sokoban.utils;

import main.sk.pavlovsky.sokoban.object.levelObject.*;

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
