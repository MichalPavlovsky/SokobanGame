package main.sk.pavlovsky.sokoban;

import main.sk.pavlovsky.sokoban.Inputs.ConsoleDirection;
import main.sk.pavlovsky.sokoban.object.levelObject.Map;
import main.sk.pavlovsky.sokoban.Inputs.Direction;

import java.util.ArrayList;


public class Game {
    private ArrayList<Map> maps;
    private boolean running=false;
    private Map activeMap;

    public Game(ArrayList<Map> maps) {
        this.maps = maps;
    }
    public Map getMap(int size){
        if (size>maps.size()) throw new RuntimeException("Inccorect index of map");
        return maps.get(size);
    }
    public void loop(){
        while(running){
            render();
            Direction d = input();
            update(d);
        }
    }

    public void start(){
        this.running=true;
        this.activeMap=maps.get(0);
        loop();
    }


    private void update(Direction direction) {
        switch (direction) {
            case QUIT :this.running = false;
            case UP : this.activeMap.getPlayer().move(0, -1); break;
            case DOWN :  this.activeMap.getPlayer().move(0,1); break;
            case LEFT : this.activeMap.getPlayer().move(-1,0); break;
            case RIGHT : this.activeMap.getPlayer().move(1,0); break;
            default:break; //how to set break with expression
        }

    }
    private Direction input() {
        return ConsoleDirection.getDirection();
    }
    private void render() {
        System.out.println(this.activeMap.toString());
    }
}
