import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS extends Algorithm {

    public Node solve(Node Goal, Node begin, String moveOrder) {
        Node solved = null;

        if (Goal.isGoal(begin))
            solved = begin;

        Queue<Node> openStates = new LinkedList<Node>();
        Set<Node> closedStates = new HashSet<Node>();

        openStates.add(begin);

        while (!openStates.isEmpty() && solved == null) {
            Node atm = openStates.remove();

            if (maxDepth < atm.depth)
                maxDepth = atm.depth;

            closedStates.add(atm);
            atm.makeNeighbours(moveOrder);

            for (Node n : atm.neighbours) {
                if (Goal.isGoal(n)) {
                    solved = n;
                }
                if (!closedStates.contains(n) && !openStates.contains(n)) {
                    openStates.add(n);
                }
            }
        }
        this.visitedStates = closedStates.size() + openStates.size();
        this.processedStates = closedStates.size();
        return solved;
    }

}
