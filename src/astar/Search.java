package astar;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Search {

    public static Result search(Board board, State startState, Position goalPos, Heuristic heuristic) {
        PriorityQueue<OrderedIterState> queue = new PriorityQueue<>();
        queue.add(new OrderedIterState(new IterState(startState), startState.cost));

        int numExpanded = 0;

        OrderedIterState currentState = null;

        while (!queue.isEmpty()) {
            currentState = queue.poll();

            if (currentState.item.current.x == goalPos.x && currentState.item.current.y == goalPos.y)
                break;

            neighbor: for (State.Neighbor n : currentState.item.current.getNeighborStates(board)) {
                if (currentState.item.previous.size() > 0) {
                    Action prevAction = currentState.item.previous.get(currentState.item.previous.size() - 1).action;
                    if (prevAction == Action.TURN_LEFT && n.action == Action.TURN_RIGHT)
                        continue;
                    if (prevAction == Action.TURN_RIGHT && n.action == Action.TURN_LEFT)
                        continue;

                    for (IterState.IterEntry previous : currentState.item.previous) {
                        if(previous.state.x == currentState.item.current.x
                                && previous.state.y == currentState.item.current.y
                                && previous.state.facing == currentState.item.current.facing
                                && previous.state.cost <= currentState.item.current.cost) {
                            continue neighbor;
                        }
                    }
                }

                for (OrderedIterState next : queue) {
                    if (next.item.current.x == currentState.item.current.x
                            && next.item.current.y == currentState.item.current.y
                            && next.item.current.facing == currentState.item.current.facing
                            && next.item.current.cost <= currentState.item.current.cost) {
                        continue neighbor;
                    }
                }

                numExpanded++;

                if (n.state.isValid(board)) {
                    int newPriority = n.state.cost + heuristic.apply(n.state.x, n.state.y, board.squaresAround(n.state.x, n.state.y), n.state.facing, goalPos, board.squaresAround(goalPos));
                    IterState newState = new IterState(currentState.item);
                    newState.append(n.state, n.action);
                    queue.add(new OrderedIterState(newState, newPriority));
                }
            }
        }

        assert currentState != null; // should never trigger
        return new Result(currentState.item, numExpanded);
    }

    static class OrderedIterState implements Comparable<OrderedIterState> {
        IterState item;
        int priority;

        public OrderedIterState(IterState item, int priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(OrderedIterState other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public static class Result {
        public IterState state;
        public int numExpanded;

        public Result(IterState endState, int numExpanded) {
            this.state = endState;
            this.numExpanded = numExpanded;
        }
    }

}
