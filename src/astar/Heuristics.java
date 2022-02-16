package astar;

import java.util.OptionalInt;

public class Heuristics {

    public static int heuristic4(Position currentPos, OptionalInt[] squaresAroundCurrent, Facing facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        int[] distances = calcDistances(currentPos, goalPos);
        return distances[0] + distances[1];
    }

    public static int heuristic5(Position currentPos, OptionalInt[] squaresAroundCurrent, Facing facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        int[] distances = calcDistances(currentPos, goalPos);
        // add 1 if there must be a turn somewhere
        return heuristic4(currentPos, squaresAroundCurrent, facing, goalPos, squaresAroundGoal) + (distances[0] > 0 && distances[1] > 0 ? 1 : 0);
    }

    public static int heuristic6(Position currentPos, OptionalInt[] squaresAroundCurrent, Facing facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        return heuristic5(currentPos, squaresAroundCurrent, facing, goalPos, squaresAroundGoal) * 3;
    }

    public static int[] calcDistances(Position pos, Position goalPos) {
        return new int[]{Math.abs(goalPos.x - pos.x), Math.abs(goalPos.y - pos.y)};
    }

}
