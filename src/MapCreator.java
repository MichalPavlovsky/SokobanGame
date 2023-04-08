public class MapCreator {
    public MapCreator[][] createMap(int rows, int  columns) {
        MapCreator[][] map = new MapCreator[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i==1 && j==0 || j == 4 || i == 2) {
                    map[i][j] = new Wall();
                } else if (i == 1 && j == 1){
                    map[i][j] = new Man();
                } else if (i==rows - 2 && j==columns-2) {
                    map[i][j]= new Goal();
                }else if (i==1 && j==2 ) {
                    map[i][j] = new Box();
                } else {
                    map[i][j]=new Empty();
                }
            }
        }
        return map;
    }

public class Wall extends MapCreator{
    public String toString() {

    return "#";
}}
public class Empty extends MapCreator{
        public String toString(){
            return " ";
        }
}
public class Man extends MapCreator {
    public String toString() {
        return "@";
    }
}
public class Box extends MapCreator{
    public String toString(){
        return "$";
    }
}
public class Goal extends MapCreator{
    public String toString(){
        return ".";
    }
}
public class ReachGoal extends MapCreator{
    public String toString(){
        return ";";
    }
}
}