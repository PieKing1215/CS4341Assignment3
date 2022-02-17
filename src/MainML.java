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
    	File csvFile = new File("data.csv");
    	FileWriter fileWriter = new FileWriter(csvFile);
    	
    	fileWriter.write("State," + "X Distance," + "Y Distance," + "Linear Distance," + "Manhatten Cost," + "Cost\n");
    	float overallTime = 0;
    	
    	for(int i = 1; i < maxIterations+1; i++) {
    		StringBuilder line = new StringBuilder();
            Board board = Board.generateRandomBoard(10, 10);

            Position start = board.find((byte) 83); // ascii S
            board.set(start, (byte) 1);

            Position goal = board.find((byte) 71); // ascii G
            board.set(goal, (byte) 1);

            State startState = new State((byte) start.x, (byte) start.y, Facing.UP, 0);

            long startTime = System.currentTimeMillis();
            Search.Result result = Search.search(board, startState, goal, Heuristics::heuristic5);
            long timeMs = System.currentTimeMillis() - startTime;
            float timeSec = (float)timeMs/1000;
            overallTime += (timeSec);
            if(i % (maxIterations/100) == 0) {
            	System.out.println(percentage++ + "%  ---- Current Time Taken : " + overallTime + " seconds   ---- Remaining Time : " + (((overallTime/i)*maxIterations)-overallTime) + " seconds");
            }
            System.gc();
            
            for (IterState.IterEntry previous : result.state.previous) {
            	int distanceX = Math.abs(previous.state.x - goal.x);
            	int distanceY = Math.abs(previous.state.y - goal.y);
            	int cost = Math.abs(result.state.current.cost - previous.state.cost);
            	double linearDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            	double manhattanCost = (distanceX + distanceY);
            	
            	fileWriter.write(previous.action + "," + distanceX  + "," + distanceY + "," + linearDistance + ","+ manhattanCost + "," + cost + ",\n");
            }
            fileWriter.write(line.toString());
            
        }
    	fileWriter.close();
        System.out.println("100% File outputted...");
    }
}
