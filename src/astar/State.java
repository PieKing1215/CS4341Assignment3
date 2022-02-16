package astar;

import java.util.ArrayList;
import java.util.List;

public class State {
    public Position pos;
    public Facing facing;
    public Board board;
    public int cost;

    public State(Position pos, Facing facing, Board board, int cost) {
        this.pos = pos;
        this.facing = facing;
        this.board = board;
        this.cost = cost;
    }

    public State(State copy, boolean copyBoard) {
        this(new Position(copy.pos), copy.facing, copyBoard ? new Board(copy.board) : copy.board, copy.cost);
    }

    public boolean isValid() {
        return this.pos.x >= 0 && this.pos.x < this.board.width()
                && this.pos.y >= 0 && this.pos.y < this.board.height();
    }

    public List<Neighbor> getNeighborStates() {
        List<Neighbor> ret = new ArrayList<>();
        for (Action action : Action.values()) {
            State newState = action.apply(this);
            if (newState.isValid()) {
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
