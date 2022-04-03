import java.io.IOException;
import java.math.BigDecimal;

public class main {

    public static void main(String[] args) throws IOException {
        Algorithm solver = null;
        String algorithm = args[0];
        String order = args[1];
        String puzzleFile = args[2];
        String solFile = args[3];
        String statFile = args[4];
        Node solution;
        String sol = "";
        String stats = "";
        BigDecimal millis;
        BigDecimal starts;
        BigDecimal ends;

        int[][] goodOrder =
                {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };


        FileManager fileManager = new FileManager(puzzleFile);
        int[][] board = fileManager.readBoard();
        Node solved = new Node(goodOrder);
        Node toSolve = new Node(board);

        switch (algorithm) {
            case "bfs":
                solver = new BFS();
                break;
            case "dfs":
                solver = new DFS();
                break;
            case "astr":
                solver = new Astar();
                break;
        }

        //Solving puzzle with chosen algorithm and measuring elapsed time
        starts = BigDecimal.valueOf(System.nanoTime());
        solution = solver.solve(solved, toSolve, order);
        ends = BigDecimal.valueOf(System.nanoTime());

        millis = ends.subtract(starts);
        millis = millis.divide(BigDecimal.valueOf(1000000));

        //creating data output
        sol += solution.moveSet.length();
        sol += "\n";
        sol += solution.moveSet;

        stats += solution.moveSet.length();
        stats += "\n";
        stats += String.valueOf(solver.visitedStates);
        stats += "\n";
        stats += String.valueOf(solver.processedStates);
        stats += "\n";
        stats += String.valueOf(solver.maxDepth);
        stats += "\n";
        stats += String.valueOf(millis);


        // Writing collected data to file
        fileManager.writeToFile(sol, solFile);
        fileManager.writeToFile(stats, statFile);


    }
}
