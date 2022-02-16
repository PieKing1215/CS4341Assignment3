package astar;

public enum Action {
    FORWARD,
    TURN_LEFT,
    TURN_RIGHT,
    BASH_FORWARD;

    public State apply(State state, Board board) {
        State newState = null;

        switch (this) {
            case FORWARD -> {
                newState = new State(state);
                newState.x += Facing.dx(state.facing);
                newState.y += Facing.dy(state.facing);
                if (newState.isValid(board)) {
                    newState.cost += board.get(newState.x, newState.y);
                }
            }
            case TURN_LEFT -> {
                newState = new State(state);
                switch (state.facing) {
                    case Facing.UP -> newState.facing = Facing.LEFT;
                    case Facing.DOWN -> newState.facing = Facing.RIGHT;
                    case Facing.LEFT -> newState.facing = Facing.DOWN;
                    case Facing.RIGHT -> newState.facing = Facing.UP;
                }
                if (newState.isValid(board)) {
                    newState.cost += (int) Math.ceil(board.get(newState.x, newState.y) / 2.0);
                }
            }
            case TURN_RIGHT -> {
                newState = new State(state);
                switch (state.facing) {
                    case Facing.UP -> newState.facing = Facing.RIGHT;
                    case Facing.DOWN -> newState.facing = Facing.LEFT;
                    case Facing.LEFT -> newState.facing = Facing.UP;
                    case Facing.RIGHT -> newState.facing = Facing.DOWN;
                }
                if (newState.isValid(board)) {
                    newState.cost += (int) Math.ceil(board.get(newState.x, newState.y) / 2.0);
                }
            }
            case BASH_FORWARD -> {
                newState = new State(state);
                newState.x += Facing.dx(state.facing);
                newState.y += Facing.dy(state.facing);
                newState.cost += 3;
                if (newState.isValid(board)) {
                    newState = Action.FORWARD.apply(newState, board);
                }
            }
        }

        return newState;
    }
}
