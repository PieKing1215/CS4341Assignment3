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

    public IterState(IterState copy, boolean copyBoard) {
        this.current = new State(copy.current, copyBoard);
        this.previous = copy.previous.stream().map(e -> new IterEntry(e, copyBoard)).collect(Collectors.toList());
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

        public IterEntry(IterEntry copy, boolean copyBoard) {
            this(new State(copy.state, copyBoard), copy.action);
        }
    }
}
