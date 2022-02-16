import astar.Board;
import astar.Facing;
import astar.Heuristics;
import astar.IterState;
import astar.Position;
import astar.Search;
import astar.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //String boardFile = args[0];
        //Board board = loadBoard(boardFile);
    	Board board = Board.generateRandomBoard(10,10);

        Position start = board.find(83); // ascii S
        board.set(start, 1);

        Position goal = board.find(71); // ascii G
        board.set(goal, 1);

        State startState = new State(start, Facing.UP, board, 0);

        long startTime = System.currentTimeMillis();
        Search.Result result = Search.search(startState, goal, Heuristics::heuristic5);
        long timeMs = System.currentTimeMillis() - startTime;

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

    }

    static Board loadBoard(String fName) throws IOException {
        File file = new File(fName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<int[]> rows = new ArrayList<>();
        while (br.ready()) {
            String line = br.readLine();
            int[] row = Arrays.stream(line.split("\t")).mapToInt(s -> {
                if (s.equals("S")) {
                    return 83;
                } else if (s.equals("G")) {
                    return 71;
                } else {
                    return Integer.parseInt(s);
                }
            }).toArray();

            rows.add(row);
        }

        int[][] nums = rows.toArray(new int[rows.size()][]);

        return new Board(nums);
    }
}
