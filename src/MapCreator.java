public class MapCreator {
    public LevelObject[][] createMap(int rows, int columns) {
        LevelObject[][] map = new LevelObject[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i == 1 && j == 0 || j == 4 || i == 2) {
                    map[i][j] = new LevelObject.Wall();
                } else if (i == 1 && j == 1) {
                    map[i][j] = new LevelObject.Man();
                } else if (i == rows - 2 && j == columns - 2) {
                    map[i][j] = new LevelObject.Goal();
                } else if (i == 1 && j == 2) {
                    map[i][j] = new LevelObject.Box();
                } else {
                    map[i][j] = new LevelObject.Empty();
                }
            }
        }
        return map;
    }
}