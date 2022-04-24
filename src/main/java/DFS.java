import java.util.*;

public class DFS extends Algorithm {
    public Node solve(Node Goal, Node Begin, String moveOrder) {
        Node solved = null;

        if (Goal.isGoal(Begin)) {
            solved = Begin;
        }

        LinkedList<Node> openStates = new LinkedList<Node>();
        Map<Integer,Node> closedStates = new HashMap<>();

        openStates.push(Begin);
        visitedStates++;

        while (!openStates.isEmpty() && solved == null) {
            Node atm = openStates.pop();

            if (atm.depth > 20) {
                continue;
            }

            if (maxDepth < atm.depth) {
                maxDepth = atm.depth;
            }


            closedStates.put(atm.hashCode(),atm);
            atm.makeNeighbours(moveOrder);
            atm.reverseNeighbours();


            for (Node n : atm.neighbours) {
                if (Goal.isGoal(n)) {
                    solved = n;
                    break;
                }
                if (!closedStates.containsKey(n.hashCode()) && !openStates.contains(n)) {
                    openStates.push(n);
                    visitedStates++;
                }
            }
        }
        this.processedStates = closedStates.size();

        if (solved != null) {
            if(maxDepth < solved.depth ) {
                maxDepth = solved.depth;
            }
        }
        return solved;
    }
}
