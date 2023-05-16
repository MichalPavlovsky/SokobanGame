package main.sk.pavlovsky.sokoban;

import main.sk.pavlovsky.sokoban.Inputs.Inputter;
import main.sk.pavlovsky.sokoban.Inputs.SwingInput;
import main.sk.pavlovsky.sokoban.object.levelActor.Box;
import main.sk.pavlovsky.sokoban.object.levelObject.Goal;
import main.sk.pavlovsky.sokoban.object.levelObject.Map;
import main.sk.pavlovsky.sokoban.Inputs.Direction;
import main.sk.pavlovsky.sokoban.object.levelObject.Wall;
import main.sk.pavlovsky.sokoban.render.Renderer;
import main.sk.pavlovsky.sokoban.render.SwingRenderer;
import main.sk.pavlovsky.sokoban.render.TextureFactory;


import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Optional;


public class Game {
    Renderer renderer;
    Inputter inputter;
    private LinkedList<Map> maps;
    private boolean running = false;
    private Map activeMap;
    private static BufferedImage texturePlayer = TextureFactory.DOWN0;

    public static BufferedImage getTexturePlayer() {
        return texturePlayer;
    }

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

    }

    public void loop() {
        while (running) {
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
                setTexturePlayer(TextureFactory.UP0);
                break;
            case DOWN:
                move(0, 1);
                setTexturePlayer(TextureFactory.DOWN0);
                break;
            case LEFT:
                move(-1, 0);
                setTexturePlayer(TextureFactory.LEFT0);
                break;
            case RIGHT:
                move(1, 0);
                setTexturePlayer(TextureFactory.RIGHT0);
                break;
            default:
                break; //how to set break with expression
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
        this.activeMap.getPlayer().move(dx, dy);
        this.activeMap.getPlayer().addSteps(1);
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

