package astar;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class Heuristics {

    public static List<Heuristic> all() {
        return Arrays.asList(
                Heuristics::heuristic1,
                Heuristics::heuristic2,
                Heuristics::heuristic3,
                Heuristics::heuristic4,
                Heuristics::heuristic5,
                Heuristics::heuristic6,
                Heuristics::heuristic7
        );
    }

    public static int heuristic1(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        return 0;
    }

    public static int heuristic2(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        int[] distances = calcDistances(x, y, goalPos);
        return Math.min(distances[0], distances[1]);
    }

    public static int heuristic3(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        int[] distances = calcDistances(x, y, goalPos);
        return Math.max(distances[0], distances[1]);
    }

    public static int heuristic4(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        int[] distances = calcDistances(x, y, goalPos);
        return distances[0] + distances[1];
    }

    public static int heuristic5(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        int[] distances = calcDistances(x, y, goalPos);
        // add 1 if there must be a turn somewhere
        return heuristic4(x, y, squaresAroundCurrent, facing, goalPos, squaresAroundGoal) + (distances[0] > 0 && distances[1] > 0 ? 1 : 0);
    }

    public static int heuristic6(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
        return heuristic5(x, y, squaresAroundCurrent, facing, goalPos, squaresAroundGoal) * 3;
    }

    public static int heuristic7(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal) {
    	int[] distances = calcDistances(x, y, goalPos);
    	
        return (int) ((2.9301 * distances[0]) + (2.9496 * distances[1]) - 1.0377);
    }

    public static int[] calcDistances(byte x, byte y, Position goalPos) {
        return new int[]{Math.abs(goalPos.x - x), Math.abs(goalPos.y - y)};
    }

}
