package main.sk.pavlovsky.sokoban;

import main.sk.pavlovsky.sokoban.levelObject.Empty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LoaderLevel {
    private List<ArrayList<String>> rawLevels;

    public LoaderLevel() {
        this.rawLevels = new ArrayList<>(); // aky je rozdiel medzi tymto konstruktorom a klasicky cez insert?
    }

    public ArrayList<Map> loadLevels(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/" + path))) {
            String line = reader.readLine();
            ArrayList<String> level = new ArrayList<>();
            while (line != null) {
                if (line.startsWith(";")) {
                    if (level.size() > 0) {
                        this.rawLevels.add((ArrayList<String>) level.clone());
                    }
                    level.clear();
                } else {
                    if (!line.isEmpty()) level.add(line);
                }
                line = reader.readLine();
            }
            this.rawLevels.add(level);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error reading a file");
        }


        ArrayList<Map> levels = new ArrayList<>();
        for (ArrayList<String> l : this.rawLevels) {
            levels.add(parseLevel(l));
        }
        return levels;
    }

    private Map parseLevel(ArrayList<String> raw) {
        Optional<String> maxSize = raw.stream().max(Comparator.comparingInt(String::length));
        if (!maxSize.isPresent()) throw new RuntimeException("Unknown max value");
        MapFactory factory = new MapFactory();
        Map map = new Map(maxSize.get().length(),raw.size());

        for (int y = 0; y < raw.size(); y++) {
            for (int x = 0; x < maxSize.get().length(); x++) {
                if (x >= raw.get(y).length()) {
                    map.setMapObject(x, y, new Empty());
                } else {
                    char c = raw.get(y).charAt(x);
                    if (c == '@') {
                        map.createPlayer(x, y);
                    }
                    if (c == '$' || c == '*') {
                        map.createBox(x, y);
                    }
                    map.setMapObject(x,y,  factory.fromInputFile(c));

                }
            }
        }
        return map;
    }
}

