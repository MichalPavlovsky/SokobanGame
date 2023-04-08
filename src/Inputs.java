import java.util.Scanner;

public class Inputs {
    public static void main(String[] args) {
        int count = 0;
        MapCreator[][] map = new MapCreator().createMap(3, 5);
        while(count<2) {
            int[] index = new int[2];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] instanceof MapCreator.Man){
                        index[0]= i;
                        index[1]=j;
                    }
                }
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j].toString());
                }
                System.out.println();
            }
            int manPos1 = index[0];
            int manPos2 = index[1];
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("d") && map[manPos1][manPos2 + 1] instanceof MapCreator.Box
                    && map[manPos1][manPos2 + 2] instanceof MapCreator.Empty) {
                map[manPos1][manPos2 + 1] = new MapCreator.Man();
                map[manPos1][manPos2 + 2] = new MapCreator.Box();
            } else if (input.equals("d") && map[manPos1][manPos2 + 1] instanceof MapCreator.Box
                    && map[manPos1][manPos2 + 2] instanceof MapCreator.Goal) {
                map[manPos1][manPos2 + 1] = new MapCreator.Man();
                map[manPos1][manPos2] = new MapCreator.Empty();
                map[manPos1][manPos2 + 2] = new MapCreator.ReachGoal();
            } else {
                System.out.println("bad");

            }
            MapCreator[][] newMap = map;
            map = newMap;
        }
    }
}
