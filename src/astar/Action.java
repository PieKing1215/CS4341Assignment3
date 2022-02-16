package astar;

public enum Action {
    FORWARD,
    TURN_LEFT,
    TURN_RIGHT,
    BASH_FORWARD;

    public State apply(State state, Board board) {
        State newState = null;

        switch (this) {
            case FORWARD:
                newState = new State(state);
                newState.pos.x += state.facing.dx;
                newState.pos.y += state.facing.dy;
                if (newState.isValid(board)) {
                    newState.cost += board.get(newState.pos);
                }
                break;
            case TURN_LEFT:
                newState = new State(state);
                switch (state.facing) {
                    case UP:
                        newState.facing = Facing.LEFT;
                        break;
                    case DOWN:
                        newState.facing = Facing.RIGHT;
                        break;
                    case LEFT:
                        newState.facing = Facing.DOWN;
                        break;
                    case RIGHT:
                        newState.facing = Facing.UP;
                        break;
                }

                if (newState.isValid(board)) {
                    newState.cost += (int) Math.ceil(board.get(newState.pos) / 2.0);
                }
                break;
            case TURN_RIGHT:
                newState = new State(state);
                switch (state.facing) {
                    case UP:
                        newState.facing = Facing.RIGHT;
                        break;
                    case DOWN:
                        newState.facing = Facing.LEFT;
                        break;
                    case LEFT:
                        newState.facing = Facing.UP;
                        break;
                    case RIGHT:
                        newState.facing = Facing.DOWN;
                        break;
                }

                if (newState.isValid(board)) {
                    newState.cost += (int) Math.ceil(board.get(newState.pos) / 2.0);
                }
                break;
            case BASH_FORWARD:
                newState = new State(state);
                newState.pos.x += state.facing.dx;
                newState.pos.y += state.facing.dy;
                newState.cost += 3;
                if (newState.isValid(board)) {
                    newState = Action.FORWARD.apply(newState, board);
                }
                break;
        }

        return newState;
    }
}
