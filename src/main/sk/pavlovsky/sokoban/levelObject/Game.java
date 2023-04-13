package main.sk.pavlovsky.sokoban.levelObject;

import main.sk.pavlovsky.sokoban.Map;

import java.util.ArrayList;

public class Game {
    private ArrayList<Map> maps;

    public Game(ArrayList<Map> maps) {
        this.maps = maps;
    }
    public Map getMap(int size){
        if (size>maps.size()) throw new RuntimeException("Inccorect index of map");
        return maps.get(size);
    }
}
