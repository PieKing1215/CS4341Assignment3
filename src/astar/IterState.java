package astar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class IterState {
    public State current;
    public List<IterEntry> previous;

    public IterState(State current) {
        this.current = current;
        this.previous = new ArrayList<>();
    }

    public IterState(IterState copy) {
        this.current = copy.current;
        this.previous = new ArrayList<>();
        for (IterEntry c : copy.previous) {
            this.previous.add(new IterEntry(c));
        }
    }

    public void append(State state, Action action) {
        this.previous.add(new IterEntry(this.current, action));
        this.current = state;
    }

    public static class IterEntry {
        public State state;
        public Action action;

        public IterEntry(State state, Action action) {
            this.state = state;
            this.action = action;
        }

        public IterEntry(IterEntry copy) {
            this(new State(copy.state), copy.action);
        }
    }
}
