import java.io.IOException;
import java.math.BigDecimal;

public class main {

    public static void main(String[] args) throws IOException {
        Algorithm solver = null;
        String algorithm = args[0];
        String order = args[1];
        String puzzleFile=args[2];
        String solFile=args[3];
        String statFile=args[4];
        Node solution = null;
        String sol="";
        String stats="";
        BigDecimal millis;
        BigDecimal starts= BigDecimal.valueOf(0);
        BigDecimal ends= BigDecimal.valueOf(0);




        FileManager fr = new FileManager(puzzleFile);
        int[][] goodOrder = fr.generateValidBoard();
        int[] boardSize=fr.readBoardSize();
        int[][] board = fr.readBoard();
        Node solved = new Node(goodOrder,boardSize);
        Node toSolve = new Node(board,boardSize);



        switch(algorithm){
            case "bfs":
                solver= new BFS();
                starts = BigDecimal.valueOf(System.nanoTime());
                solution=solver.solve(solved,toSolve,order);
                ends = BigDecimal.valueOf(System.nanoTime());
                break;
            case "dfs":
                solver = new DFS();
                starts = BigDecimal.valueOf(System.nanoTime());
                solution=solver.solve(solved,toSolve,order);
                ends = BigDecimal.valueOf(System.nanoTime());
                break;
            case "astr":
                solver= new Astar();
                starts = BigDecimal.valueOf(System.nanoTime());
                solution=solver.solve(solved,toSolve,order);
                ends = BigDecimal.valueOf(System.nanoTime());
                break;
        }

        millis=ends.subtract(starts);
        millis=millis.divide(BigDecimal.valueOf(1000000));

        if(solution!=null) {
            sol += solution.moveSet.length();
            sol += "\n";
            sol += solution.moveSet;
            stats += solution.moveSet.length();
        }
        else{
            sol+="-1";
            sol += "\n";
            sol+="-1";
            stats +="-1";
        }


        stats+="\n";
        stats+=String.valueOf(solver.visitedStates);
        stats+="\n";
        stats+=String.valueOf(solver.processedStates);
        stats+="\n";
        stats+=String.valueOf(solver.maxDepth);
        stats+="\n";
        stats+=String.valueOf(millis);

        fr.writeToFile(sol,solFile);
        fr.writeToFile(stats,statFile);

/*        System.out.println(sol);
        System.out.println(stats);*/

    }
}