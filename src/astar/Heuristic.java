package astar;

import java.util.OptionalInt;

public interface Heuristic {
    int apply(Position currentPos, OptionalInt[] squaresAroundCurrent, Facing facing, Position goalPos, OptionalInt[] squaresAroundGoal);
}
