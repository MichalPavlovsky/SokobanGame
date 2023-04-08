public class Main {
    public static void main(String[] args) {
        MapCreator[][] map = new MapCreator().createMap(3,5);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j].toString());
            }
            System.out.println();
        }

    }
}