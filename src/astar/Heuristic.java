package astar;

import java.util.OptionalInt;

public interface Heuristic {
    int apply(byte x, byte y, OptionalInt[] squaresAroundCurrent, byte facing, Position goalPos, OptionalInt[] squaresAroundGoal);
}
