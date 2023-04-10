public abstract class LevelObject {
    public abstract String toString();

    public static class Wall extends LevelObject {
        public String toString() {
            return "#";
        }
    }

    public static class Empty extends LevelObject {
        public String toString() {
            return " ";
        }
    }

    public static class Man extends LevelObject {
        public String toString() {
            return "@";
        }
    }

    public static class Box extends LevelObject {
        public String toString() {
            return "$";
        }
    }

    public static class Goal extends LevelObject {
        public String toString() {
            return ".";
        }
    }

    public static class ReachGoal extends LevelObject {
        public String toString() {
            return ";";
        }
    }
}
