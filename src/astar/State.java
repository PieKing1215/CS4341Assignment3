package astar;

import java.util.ArrayList;
import java.util.List;

public class State {
    public byte x;
    public byte y;
    public byte facing;
    public int cost;

    public State(byte x, byte y, byte facing, int cost) {
        this.x = x;
        this.y = y;
        this.facing = facing;
        this.cost = cost;
    }

    public State(State copy) {
        this(copy.x, copy.y, copy.facing, copy.cost);
    }

    public boolean isValid(Board board) {
        return this.x >= 0 && this.x < board.width()
                && this.y >= 0 && this.y < board.height();
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
