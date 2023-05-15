package main.sk.pavlovsky.sokoban.render;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import main.sk.pavlovsky.sokoban.Game;
import main.sk.pavlovsky.sokoban.object.levelObject.Goal;

import java.io.IOException;
import java.util.List;

public class LanternaRenderer implements Renderer{
    private Screen screen;
    public LanternaRenderer(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void init() {
        try {
            this.screen.startScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deinit() {
        try {
            this.screen.stopScreen();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Game game) {
        renderMap(game);
        renderUI(game);
        try {
            this.screen.refresh();
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("It is not possible to render.");
        }
    }

    @Override
    public void clear() {
        this.screen.clear();
    }
    private void renderMap(Game game) {
        List<String> list = game.getActiveMap().toList();
        int xOff = 5;
        int yOff = 5;
        for (int y = 0; y < list.size(); y++) {
            for (int x = 0; x < list.get(y).length(); x++) {
                this.screen.setCharacter(x + xOff, y + yOff, new TextCharacter(list.get(y).charAt(x)));
            }
        }
}
    private void renderUI(Game game) {
        int steps = game.getActiveMap().getPlayer().getSteps();
        TextCharacter[] textCharactersSteps = TextCharacter.fromString("Steps: " + Integer.toString(steps));
        for (int i = 0; i < textCharactersSteps.length; i++) {
            this.screen.setCharacter(2 + i, 2, textCharactersSteps[i]);
        }
        long boxesOnGoal = game.getActiveMap().getBoxes().stream()
                .filter(box -> game.getActiveMap().getLevelObject(box.getX(), box.getY()) instanceof Goal).count();
        TextCharacter[] textCharactersBoxes = TextCharacter.fromString("Score: " + Long.toString(boxesOnGoal) + "/" +
                Integer.toString(game.getActiveMap().getBoxes().size()));
        for (int i = 0; i < textCharactersBoxes.length; i++) {
            this.screen.setCharacter(15 + i, 2, textCharactersBoxes[i]);
        }
        if (game.isLevelFinished()) {
            TextCharacter[] textCharactersSuccess = TextCharacter.fromString("Level completed, Press N to continue");
            for (int i = 0; i< textCharactersSuccess.length; i++)
                this.screen.setCharacter(2+i,3,textCharactersSuccess[i]);
        }
    }
    public Screen getScreen() {
        return this.screen;
    }
}
