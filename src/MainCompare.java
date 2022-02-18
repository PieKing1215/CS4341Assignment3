import astar.Board;
import astar.Facing;
import astar.Heuristic;
import astar.Heuristics;
import astar.IterState;
import astar.Position;
import astar.Search;
import astar.State;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Compares heuristics 5, 6, and 7 on 10 random boards
 */
public class MainCompare {

    public static void main(String[] args) {

        Heuristic[] heuristics = new Heuristic[]{ Heuristics::heuristic5, Heuristics::heuristic6, Heuristics::heuristic7 };
        int[] sumCosts = new int[heuristics.length];
        int[] sumExpanded = new int[heuristics.length];
        float[] sumBranching = new float[heuristics.length];
        long[] sumTime = new long[heuristics.length];

    	int n = 10;
    	for(int i = 0; i < n; i++) {
            System.out.println("Board " + i);
            Board board = Board.generateRandomBoard(30, 30);

            Position start = board.find((byte) 83); // ascii S
            board.set(start, (byte) 1);

            Position goal = board.find((byte) 71); // ascii G
            board.set(goal, (byte) 1);

            State startState = new State((byte) start.x, (byte) start.y, Facing.UP, 0);

            for(int h = 0; h < heuristics.length; h++) {
                System.out.println("- " + h);
                long startTime = System.currentTimeMillis();
                Search.Result result = Search.search(board, startState, goal, heuristics[h]);
                long timeMs = System.currentTimeMillis() - startTime;

                sumTime[h] += timeMs;
                sumCosts[h] += result.state.current.cost;
                sumExpanded[h] += result.numExpanded;
                float meanEffBranchingFactor = (float)Math.pow(result.numExpanded, 1.0 / (result.state.previous.size() + 1));
                sumBranching[h] += meanEffBranchingFactor;
            }
        }
//    	fileWriter.close();
        System.out.println("Done.");

        for(int h = 0; h < heuristics.length; h++) {
            System.out.println("Heuristic #" + (5 + h) + ":");
            System.out.println("Avg solution cost: " + sumCosts[h] / 10f);
            System.out.println("Avg # expanded nodes: " + sumExpanded[h] / 10f);
            System.out.println("Avg mean effective branching factor: " + sumBranching[h] / 10f);
            System.out.println("Avg time: " + sumTime[h] / 10 + " ms");
        }

    }
}
