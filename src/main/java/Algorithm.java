public abstract class Algorithm {

    public int visitedStates = 0;
    public int processedStates = 0;
    public int maxDepth = 0;

    public abstract Node solve(Node Goal, Node Begin, String moveOrder);
}
