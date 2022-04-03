import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DFS extends Algorithm {
    public Node solve(Node Goal, Node Begin, String moveOrder) {
        Node solved = null;

        if (Goal.isGoal(Begin)) {
            solved = Begin;
        }

        LinkedList<Node> openStates = new LinkedList<Node>();
        Set<Node> closedStates = new HashSet<Node>();

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

            closedStates.add(atm);
            atm.makeNeighbours(moveOrder);
            atm.reverseNeighbours();


            for (Node n : atm.neighbours) {
                if (Goal.isGoal(n)) {
                    solved = n;
                }
                if (!closedStates.contains(n) && !openStates.contains(n)) {
                    openStates.push(n);
                    visitedStates++;
                }
            }
        }
        this.processedStates = closedStates.size();
        return solved;
    }
}
