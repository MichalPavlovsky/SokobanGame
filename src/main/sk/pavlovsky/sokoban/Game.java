package main.sk.pavlovsky.sokoban;

import main.sk.pavlovsky.sokoban.Inputs.Inputter;
import main.sk.pavlovsky.sokoban.Inputs.SwingInput;
import main.sk.pavlovsky.sokoban.Inputs.View;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;
import main.sk.pavlovsky.sokoban.object.levelActor.Player;
import main.sk.pavlovsky.sokoban.object.levelObject.Goal;
import main.sk.pavlovsky.sokoban.object.levelObject.Map;
import main.sk.pavlovsky.sokoban.Inputs.Direction;
import main.sk.pavlovsky.sokoban.object.levelObject.Wall;
import main.sk.pavlovsky.sokoban.render.Renderer;
import main.sk.pavlovsky.sokoban.render.SwingRenderer;
import main.sk.pavlovsky.sokoban.render.TextureFactory;



import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Optional;

import static main.sk.pavlovsky.sokoban.Inputs.View.*;
import static main.sk.pavlovsky.sokoban.Inputs.View.UPP;
import static main.sk.pavlovsky.sokoban.render.TextureFactory.*;


public class Game {
    Renderer renderer;
    Inputter inputter;
    Instant lastloop;
    private View view=View.DOWNN;

    private LinkedList<BufferedImage> skinUp;
    private LinkedList<BufferedImage> skinDown;
    private LinkedList<BufferedImage> skinLeft;
    private LinkedList<BufferedImage> skinRight;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private LinkedList<Map> maps;
    private boolean running = false;
    private Map activeMap;

    public boolean isMovePlayer() {
        return movePlayer;
    }

    public void setMovePlayer(boolean movePlayer) {
        this.movePlayer = movePlayer;
    }

    private boolean movePlayer =false;
    private boolean moveBox = false;

    public boolean isMoveBox() {
        return moveBox;
    }

    public void setMoveBox(boolean moveBox) {
        this.moveBox = moveBox;
    }

    private static final int FPS = 30;
    private static final int REFRESH= 1000/FPS;



    public Map getActiveMap() {
        return activeMap;
    }

    public Game(LinkedList<Map> maps) {

        this.maps = maps;
//        renderer = new SwingRenderer();
//        inputter = new LanternaInput(((LanternaRenderer)renderer).getScreen());
        renderer= new SwingRenderer();
        inputter= new SwingInput(((SwingRenderer)renderer).getFrame());
        this.lastloop = Instant.now();
        this.skinUp = new LinkedList<>();
        this.skinDown= new LinkedList<>();
        this.skinLeft = new LinkedList<>();
        this.skinRight = new LinkedList<>();
    }

    public void loop() {
        while (running) {
            while (isMovePlayer()) {
                Instant now = Instant.now();
                long delta = Duration.between(lastloop,now).toMillis();
                checkList();
                if (delta>REFRESH){
                    moveOff(getView());
                    render();
                    this.lastloop=now;}
            }
            render();
            Direction d = input();
            update(d);
        }
    }

    public void start() {
        this.running = true;
        this.activeMap = maps.pop();
        renderer.init();
        loop();
        renderer.deinit();
    }


    private void update(Direction direction) {
        switch (direction) {
            case QUIT:
                this.running = false;
            case NEXT: {
                if (isLevelFinished()) {
                    this.renderer.clear();
                    this.activeMap = maps.pop();
                }
            } break;
            case UP:
                move(0, -1);
                this.setView(View.UPP);
                break;
            case DOWN:
                move(0, 1);
                this.setView(View.DOWNN);
                break;
            case LEFT:
                move(-1, 0);
                this.setView(View.LEFTT);
                break;
            case RIGHT:
                move(1, 0);
                this.setView(View.RIGHTT);
                break;
            default:
                break;
        }
    }
    private void moveOff(View view) {
        Player getP=this.activeMap.getPlayer();
        int xo=getP.getXOff();
        int yo=getP.getYOff();
        if (view== LEFTT) {
            if (getP.getXOff()>-64) {
                getP.setXOff(-4);
                if (xo==0||xo==-16||xo==-32||xo==-48){
                    getP.setTexture(this.skinLeft.pop());
                }
            }else {setMovePlayer(false);
            setMoveBox(false);
            getP.setXOff2(0);
            getP.setXBOff(0);
            getP.setYBOff(0);}
        }else if (view== RIGHTT) {
            if (getP.getXOff()<64) {
                getP.setXOff(4);
                if (xo==0||xo==16||xo==32||xo==48){
                    getP.setTexture(this.skinRight.pop());}
            }else {setMovePlayer(false);
            setMoveBox(false);
            getP.setXOff2(0);
            getP.setXBOff(0);
            getP.setYBOff(0);}
        }else if (view== DOWNN) {
            if (getP.getYOff()<64) {
                getP.setYOff(4);
                if (yo==0||yo==16||yo==32||yo==48){
                    getP.setTexture(this.skinDown.pop());}
            }else {setMovePlayer(false);
            setMoveBox(false);
            getP.setyOff2(0);
            getP.setXBOff(0);
            getP.setYBOff(0);}
        }else if (view== UPP) {
            if (getP.getYOff()>-64) {
                getP.setYOff(-4);
                if (yo==0||yo==-16||yo==-32||yo==-48){
                    getP.setTexture(this.skinUp.pop());}
            }else {setMovePlayer(false);
            setMoveBox(false);
            getP.setyOff2(0);
            getP.setXBOff(0);
            getP.setYBOff(0);}
        }
    }

    private void move(int dx, int dy) {
        if (dx > 1 || dx < -1 || dy > 1 || dy < -1) throw new RuntimeException("Big movement");
        int x = this.activeMap.getPlayer().getX();
        int y = this.activeMap.getPlayer().getY();
        if (x + dx > this.activeMap.getWidth() || y + dy > this.activeMap.getHeight() || this.activeMap.getLevelObject(x + dx, y + dy) instanceof Wall) {
            return;
        }
        Optional<Box> boxOptional = this.activeMap.getBoxes().stream().filter(it -> it.getX() == x + dx && it.getY() == dy + y).findFirst();
        if (!boxOptional.isPresent()) {
            this.activeMap.getPlayer().move(dx, dy);
            this.activeMap.getPlayer().addSteps(1);
            setMovePlayer(true);
            return;
        }
        if (x + 2 * dx > this.activeMap.getWidth() || y + 2 * dy > this.activeMap.getHeight() || this.activeMap.getLevelObject(x + 2 * dx, y + 2 * dy) instanceof Wall) {
            return;
        }
        Optional<Box> boxOptional1 = this.activeMap.getBoxes().stream().filter(it -> it.getX() == x + 2 * dx && it.getY() == y + 2 * dy).findFirst();
        if (boxOptional1.isPresent())
            return;

        Box box = boxOptional.get();
        box.move(dx, dy);
        setMoveBox(true);
        this.activeMap.getPlayer().move(dx, dy);
        this.activeMap.getPlayer().addSteps(1);
        setMovePlayer(true);
    }


    private void render() {
        renderer.render(this);
    }

    private Direction input() {
        return inputter.getInput();
    }

    public boolean isLevelFinished() {
        long onGoal = this.activeMap.getBoxes().stream()
                .filter(it -> this.activeMap.getLevelObject(it.getX(), it.getY()) instanceof Goal).count();
        int goals = this.activeMap.getBoxes().size();
        return goals == onGoal;
    }
    private void checkList(){
        if (skinUp.isEmpty()){
            setListSkinUp();
        }
        if (skinRight.isEmpty()){
            setListSkinRight();
        }
        if (skinLeft.isEmpty()){
            setListSkinLeft();
        }
        if (skinDown.isEmpty()){
            setListSkinDown();
        }
    }
    private void setListSkinRight(){
        this.skinRight.add(RIGHT0);
        this.skinRight.add(RIGHT1);
        this.skinRight.add(RIGHT0);
        this.skinRight.add(RIGHT2);}
    private void setListSkinUp(){
        this.skinUp.add(UP0);
        this.skinUp.add(UP1);
        this.skinUp.add(UP0);
        this.skinUp.add(UP2);}
    private void setListSkinLeft(){
        this.skinLeft.add(LEFT0);
        this.skinLeft.add(LEFT1);
        this.skinLeft.add(LEFT0);
        this.skinLeft.add(LEFT2);}
    private void setListSkinDown(){
        this.skinDown.add(DOWN0);
        this.skinDown.add(DOWN1);
        this.skinDown.add(DOWN0);
        this.skinDown.add(DOWN2);
    }
}

