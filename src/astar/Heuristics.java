package astar;

public class Heuristics {

    public static int heuristic4(State state, Position goalPos) {
        int[] distances = calcDistances(state.pos, goalPos);
        return distances[0] + distances[1];
    }

    public static int heuristic5(State state, Position goalPos) {
        int[] distances = calcDistances(state.pos, goalPos);
        // add 1 if there must be a turn somewhere
        return heuristic4(state, goalPos) + (distances[0] > 0 && distances[1] > 0 ? 1 : 0);
    }

    public static int heuristic6(State state, Position goalPos) {
        return heuristic5(state, goalPos) * 3;
    }

    public static int[] calcDistances(Position pos, Position goalPos) {
        return new int[]{Math.abs(goalPos.x - pos.x), Math.abs(goalPos.y - pos.y)};
    }

}
