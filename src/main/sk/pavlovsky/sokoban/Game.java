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


public class Game {
    Renderer renderer;
    Inputter inputter;
    Instant lastloop;
    private View view=View.DOWNN;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private LinkedList<Map> maps;
    private boolean running = false;
    private Map activeMap;
    private static BufferedImage texturePlayer = TextureFactory.DOWN0;

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

    private static final int FPS = 15;
    private static final int REFRESH= 1000/FPS;

    public static void setTexturePlayer(BufferedImage texturePlayer) {
        Game.texturePlayer = texturePlayer;
    }

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

    }

    public void loop() {
        while (running) {
            while (isMovePlayer()) {
                Instant now = Instant.now();
                long delta = Duration.between(lastloop,now).toMillis();
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
                this.activeMap.getPlayer().setTexture(TextureFactory.UP0);
                break;
            case DOWN:
                move(0, 1);
                this.setView(View.DOWNN);
                this.activeMap.getPlayer().setTexture(TextureFactory.DOWN0);
                break;
            case LEFT:
                move(-1, 0);
                this.setView(View.LEFTT);
                this.activeMap.getPlayer().setTexture(TextureFactory.LEFT0);
                break;
            case RIGHT:
                move(1, 0);
                this.setView(View.RIGHTT);
                this.activeMap.getPlayer().setTexture(TextureFactory.RIGHT0);
                break;
            default:
                break; //how to set break with expression
        }
    }
    private void moveOff(View view) {
        Player getP=this.activeMap.getPlayer();
        if (view== LEFTT) {
            if (getP.getXOff()>-64) {
                getP.setXOff(-4);
            }else {setMovePlayer(false);
            setMoveBox(false);
            getP.setXOff2(0);
            getP.setXBOff(0);
            getP.setYBOff(0);}
        }else if (view== RIGHTT) {
            if (getP.getXOff()<64) {
                getP.setXOff(4);
            }else {setMovePlayer(false);
            setMoveBox(false);
            getP.setXOff2(0);
            getP.setXBOff(0);
            getP.setYBOff(0);}
        }else if (view== DOWNN) {
            if (getP.getYOff()<64) {
                getP.setYOff(4);
            }else {setMovePlayer(false);
            setMoveBox(false);
            getP.setyOff2(0);
            getP.setXBOff(0);
            getP.setYBOff(0);}
        }else if (view== UPP) {
            if (getP.getYOff()>-64) {
                getP.setYOff(-4);
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
}

