import astar.*;

import java.io.IOException;

public class MainML {

    public static void main(String[] args) throws IOException {
        int maxIterations = 1000;
    	int percentage = 0;
    	for(int i = 0; i < maxIterations; i++) {
            Board board = Board.generateRandomBoard(5, 5);

            Position start = board.find((byte) 83); // ascii S
            board.set(start, (byte) 1);

            Position goal = board.find((byte) 71); // ascii G
            board.set(goal, (byte) 1);

            State startState = new State(start, Facing.UP, board, 0);

            long startTime = System.currentTimeMillis();
            Search.Result result = Search.search(startState, goal, Heuristics::heuristic5);
            long timeMs = System.currentTimeMillis() - startTime;

            if(i % (maxIterations/100) == 0) {
            	System.out.println(percentage++ + "%");
            }
            
            /*
            System.out.println("GOAL REACHED:");
            System.out.println("pos = " + result.state.current.pos);
            System.out.println("facing = " + result.state.current.facing);
            System.out.println("board = " + result.state.current.board);
            System.out.println("cost = " + result.state.current.cost);
            System.out.println("# moves = " + result.state.previous.size());
            System.out.println("# expanded = " + result.numExpanded);
            System.out.println("time = " + timeMs + "ms");
            System.out.println("ACTIONS TAKEN:");
            for (IterState.IterEntry previous : result.state.previous) {
                System.out.println(previous.action);
            }
            
            */
        }
        System.out.println("GOAL REACHED:");
    }
}
