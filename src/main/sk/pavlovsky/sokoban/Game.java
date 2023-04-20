package main.sk.pavlovsky.sokoban;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import main.sk.pavlovsky.sokoban.Inputs.WindowInput;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;
import main.sk.pavlovsky.sokoban.object.levelObject.Goal;
import main.sk.pavlovsky.sokoban.object.levelObject.Map;
import main.sk.pavlovsky.sokoban.Inputs.Direction;
import main.sk.pavlovsky.sokoban.object.levelObject.Wall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Game {
    private Screen screen;
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
    public int number=0;
    public void loop(){
        while(running){
            render();
            Direction d = input();
            update(d);
        }
    }

    public void start(Screen screen){
        this.running=true;
        this.activeMap=maps.get(0);
        this.screen =screen;
        loop();
    }


    private void update(Direction direction) {
        switch (direction) {
            case QUIT :this.running = false;
            case NEXT: this.activeMap = maps.get(number+1);
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
//        return ConsoleDirection.getDirection();
        return WindowInput.getInput(this.screen);
    }
    private void render() {
        renderMap();
        renderUI();
        try {
            this.screen.refresh();
        }catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException("It is not possible to render. ");
        }
    }
    private void renderMap() {
        List<String> list = activeMap.toList();
        int xOff = 5;
        int yOff = 5;
        for (int y = 0; y < list.size(); y++) {
            for (int x = 0; x < list.get(y).length(); x++) {
                this.screen.setCharacter(x+xOff, y+yOff, new TextCharacter(list.get(y).charAt(x)));
            }
        }

    }
    private void renderUI() {
        int steps = this.activeMap.getPlayer().getSteps();
        TextCharacter[] textCharactersSteps = TextCharacter.fromString("Steps: " + Integer.toString(steps));
        for (int i = 0; i < textCharactersSteps.length; i++) {
            this.screen.setCharacter(2+i,2,textCharactersSteps[i]);
        }
        long boxesOnGoal = this.activeMap.getBoxes().stream()
                .filter(box-> this.activeMap.getLevelObject(box.getX(), box.getY()) instanceof Goal).count();
        TextCharacter[] textCharactersBoxes =  TextCharacter.fromString("Score: "+Long.toString(boxesOnGoal) + "/" +
                Integer.toString(this.activeMap.getBoxes().size()));
        for (int i = 0; i < textCharactersBoxes.length; i++) {
            this.screen.setCharacter(15+i,2,textCharactersBoxes[i]);
        }
    }
}
