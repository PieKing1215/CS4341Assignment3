package astar;

//public enum Facing {
//    UP(0, -1),
//    DOWN(0, 1),
//    LEFT(-1, 0),
//    RIGHT(1, 0);
//
//    int dx, dy;
//
//    Facing(int dx, int dy) {
//        this.dx = dx;
//        this.dy = dy;
//    }
//}

public class Facing {
    public static final byte UP = 0;
    public static final byte DOWN = 1;
    public static final byte LEFT = 2;
    public static final byte RIGHT = 3;

    public static byte dx(byte facing){
        return switch (facing) {
            case LEFT -> (byte) -1;
            case RIGHT -> (byte) 1;
            default -> (byte) 0;
        };
    }

    public static byte dy(byte facing){
        return switch (facing) {
            case UP -> (byte) -1;
            case DOWN -> (byte) 1;
            default -> (byte) 0;
        };
    }
}
