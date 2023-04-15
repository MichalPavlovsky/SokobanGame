package main.sk.pavlovsky.sokoban;

import main.sk.pavlovsky.sokoban.Inputs.ConsoleDirection;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;
import main.sk.pavlovsky.sokoban.object.levelObject.Map;
import main.sk.pavlovsky.sokoban.Inputs.Direction;
import main.sk.pavlovsky.sokoban.object.levelObject.Wall;

import java.util.ArrayList;
import java.util.Optional;


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
            case UP : move(0, -1); break;
            case DOWN :  move(0,1); break;
            case LEFT : move(-1,0); break;
            case RIGHT : move(1,0); break;
            default:break; //how to set break with expression
        }
    }
    private void move(int dx, int dy){
        if (dx> 1|| dx<-1 || dy>1 || dy <-1) throw new RuntimeException("Big movement");
        int x = this.activeMap.getPlayer().getX();
        int y = this.activeMap.getPlayer().getY();
        if (x+dx > this.activeMap.getWidth() || y+dy > this.activeMap.getHeight() || this.activeMap.getLevelObject(x+dx, y+dy) instanceof Wall) {
            return;
        }
        Optional<Box> boxOptional = this.activeMap.getBoxes().stream().filter(it -> it.getX() == x+dx && it.getY() == dy+y).findFirst();
        if (!boxOptional.isPresent()) {
            this.activeMap.getPlayer().move(dx, dy);
            return;
        }
        if (x+2*dx > this.activeMap.getWidth()||y+2*dy> this.activeMap.getHeight() || this.activeMap.getLevelObject(x+2*dx,y+2*dy) instanceof Wall){
            return;
        }
        Optional<Box> boxOptional1 = this.activeMap.getBoxes().stream().filter(it -> it.getX() == x+2*dx && it.getY() ==y+2*dy).findFirst();
        if (boxOptional1.isPresent())
            return;

        Box box = boxOptional.get();
        box.move(dx,dy);
        this.activeMap.getPlayer().move(dx,dy);
    }
    private Direction input() {
        return ConsoleDirection.getDirection();
    }
    private void render() {
        System.out.println(this.activeMap.toString());
    }
}
