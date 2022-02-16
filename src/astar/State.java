package astar;

import java.util.ArrayList;
import java.util.List;

public class State {
    public Position pos;
    public Facing facing;
    public int cost;

    public State(Position pos, Facing facing, int cost) {
        this.pos = pos;
        this.facing = facing;
        this.cost = cost;
    }

    public State(State copy) {
        this(new Position(copy.pos), copy.facing, copy.cost);
    }

    public boolean isValid(Board board) {
        return this.pos.x >= 0 && this.pos.x < board.width()
                && this.pos.y >= 0 && this.pos.y < board.height();
    }

    public List<Neighbor> getNeighborStates(Board board) {
        List<Neighbor> ret = new ArrayList<>();
        for (Action action : Action.values()) {
            State newState = action.apply(this, board);
            if (newState.isValid(board)) {
                ret.add(new Neighbor(action, newState));
            }
        }

        return ret;
    }

    public static class Neighbor {
        Action action;
        State state;

        public Neighbor(Action action, State state) {
            this.action = action;
            this.state = state;
        }
    }
}
