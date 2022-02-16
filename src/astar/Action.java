package astar;

public enum Action {
    FORWARD,
    TURN_LEFT,
    TURN_RIGHT,
    BASH_FORWARD,
    DEMOLISH;

    public State apply(State state) {
        State newState = null;

        switch (this) {
            case FORWARD:
                newState = new State(state, false);
                newState.pos.x += state.facing.dx;
                newState.pos.y += state.facing.dy;
                if (newState.isValid()) {
                    newState.cost += state.board.get(newState.pos);
                }
                break;
            case TURN_LEFT:
                newState = new State(state, false);
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

                if (newState.isValid()) {
                    newState.cost += (int) Math.ceil(state.board.get(newState.pos) / 2.0);
                }
                break;
            case TURN_RIGHT:
                newState = new State(state, false);
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

                if (newState.isValid()) {
                    newState.cost += (int) Math.ceil(state.board.get(newState.pos) / 2.0);
                }
                break;
            case BASH_FORWARD:
                newState = new State(state, false);
                newState.pos.x += state.facing.dx;
                newState.pos.y += state.facing.dy;
                newState.cost += 3;
                if (newState.isValid()) {
                    newState = Action.FORWARD.apply(newState);
                }
                break;
            case DEMOLISH:
                newState = new State(state, true);
                newState.cost += 4;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx != 0 || dy != 0) {
                            int nx = newState.pos.x + dx;
                            int ny = newState.pos.y + dy;
                            if (ny >= 0 && ny < newState.board.height()
                                    && nx >= 0 && nx < newState.board.width()) {
                                newState.board.set(nx, ny, 3);
                            }
                        }
                    }
                }
                break;
        }

        return newState;
    }
}
