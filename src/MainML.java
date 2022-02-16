import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import astar.Board;
import astar.Facing;
import astar.Heuristics;
import astar.IterState;
import astar.Position;
import astar.Search;
import astar.State;

public class MainML {

    public static void main(String[] args) throws IOException {
        int maxIterations = 10000;
    	int percentage = 0;
    	File csvFile = new File("test.csv");
    	FileWriter fileWriter = new FileWriter(csvFile);
    	
    	fileWriter.write("State," + "X Distance," + "Y Distance," + "Linear Distance," + "Cost\n");
    	
    	for(int i = 0; i < maxIterations; i++) {
    		StringBuilder line = new StringBuilder();
            Board board = Board.generateRandomBoard(15, 15);

            Position start = board.find((byte) 83); // ascii S
            board.set(start, (byte) 1);

            Position goal = board.find((byte) 71); // ascii G
            board.set(goal, (byte) 1);

            State startState = new State((byte) start.x, (byte) start.y, Facing.UP, 0);

            long startTime = System.currentTimeMillis();
            Search.Result result = Search.search(board, startState, goal, Heuristics::heuristic5);
            long timeMs = System.currentTimeMillis() - startTime;

            if(i % (maxIterations/100) == 0) {
            	System.out.println(percentage++ + "%");
            }
            System.gc();
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
            */
           //fileWriter.write(result.state.current.cost + ",");
           //fileWriter.write(result.numExpanded + ",");
           //fileWriter.write(timeMs + ",");
            for (IterState.IterEntry previous : result.state.previous) {
            	int distanceX = Math.abs(previous.state.x - goal.x);
            	int distanceY = Math.abs(previous.state.y - goal.y);
            	double linearDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            	fileWriter.write(previous.action + "," + distanceX  + "," + distanceY + "," + linearDistance + ", " + previous.state.cost + ",\n");
            }
            fileWriter.write(line.toString());
            
        }
    	fileWriter.close();
        System.out.println("100% File outputted...");
    }
}
