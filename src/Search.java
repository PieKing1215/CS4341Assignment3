import java.util.PriorityQueue;

public class Search {

    public static Result search(State startState, Position goalPos, Heuristic heuristic) {
        PriorityQueue<OrderedIterState> queue = new PriorityQueue<>();
        queue.add(new OrderedIterState(new IterState(startState), startState.cost));

        int numExpanded = 0;

        OrderedIterState currentState = null;

        while (!queue.isEmpty()) {
            currentState = queue.poll();

            if (currentState.item.current.pos.equals(goalPos))
                break;

            for (State.Neighbor n : currentState.item.current.getNeighborStates()) {
                if (currentState.item.previous.size() > 0) {
                    Action prevAction = currentState.item.previous.get(currentState.item.previous.size() - 1).action;
                    if (prevAction == Action.TURN_LEFT && n.action == Action.TURN_RIGHT)
                        continue;
                    if (prevAction == Action.TURN_RIGHT && n.action == Action.TURN_LEFT)
                        continue;
                }

                numExpanded++;

                if (n.state.isValid()) {
                    int newPriority = n.state.cost + heuristic.apply(n.state, goalPos);
                    IterState newState = new IterState(currentState.item, n.action == Action.DEMOLISH);
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

    static class Result {
        IterState state;
        int numExpanded;

        public Result(IterState endState, int numExpanded) {
            this.state = endState;
            this.numExpanded = numExpanded;
        }
    }

}
