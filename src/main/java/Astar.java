import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Astar extends Algorithm {
    public Node solve(Node Goal, Node begin, String heuristic) {
        Node solved = null;

        if (Goal.isGoal(begin)) {
            solved = begin;
        }

        PriorityQ<Node> openStates = new PriorityQ<Node>();
        Set<Node> closedStates = new HashSet<>();

        openStates.add(begin);

        while (!openStates.isEmpty() && solved == null) {

            Node atm = openStates.remove();

            if (maxDepth < atm.depth)
                maxDepth = atm.depth;

            if (Goal.isGoal(atm)) {
                solved = atm;
            }

            closedStates.add(atm);
            atm.makeNeighbours("LUDR");

            for (Node n : atm.neighbours) {
                int priority1 = 0;

                if (!closedStates.contains(n)) {
                    if (heuristic.equals("manh")) {
                        priority1 = n.calculateManhattan();
                    } else if (heuristic.equals("hamm")) {
                        priority1 = n.calculateHamming();
                    }
                    if (!openStates.contains(n)) {
                        n.priority = priority1;
                        openStates.add(n);
                    } else if (openStates.contains(n)) {
                        Node k = openStates.getNode(n);
                        if (k.priority > priority1) {
                            openStates.Update(k,priority1);
                        }
                    }
                }
            }
        }
        this.processedStates = closedStates.size();
        this.visitedStates = closedStates.size() + openStates.size();
        return solved;
    }
}