public enum Facing {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    int dx, dy;

    Facing(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
