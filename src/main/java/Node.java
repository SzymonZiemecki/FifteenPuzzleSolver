import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import java.util.List;

public class Node implements Comparable<Node> {

    public List<Node> neighbours = new ArrayList<Node>();
    public Node parent;
    public String parentMove;
    public String moveSet;
    public int depth;
    public int[][] board;
    public int zeroPosition[] = new int[2];
    public int priority;


    public Node(int[][] board) {
        this.board = board;
        this.parentMove = "none";
        this.parent = this;
        this.depth = 0;
        this.moveSet = "";
        this.priority = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    zeroPosition[0] = i;
                    zeroPosition[1] = j;
                }
            }
        }
    }

    public Node(Node state, String moveLetter) {
        this.board = copyBoard(state.board);
        this.parent = state;
        this.parentMove = moveLetter;
        this.priority = 0;
        moveSet = String.valueOf(state.moveSet);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    zeroPosition[0] = i;
                    zeroPosition[1] = j;
                }
            }
        }
    }

    public Node Move(String moveLetter) {
        if (moveLetter.equals("U")) {
            Node newNode = new Node(this, moveLetter);
            newNode.depth = this.depth + 1;
            newNode.moveSet += moveLetter;
            newNode.board[zeroPosition[0]][zeroPosition[1]] = newNode.board[zeroPosition[0] - 1][zeroPosition[1]];
            newNode.board[zeroPosition[0] - 1][zeroPosition[1]] = 0;
            newNode.zeroPosition[0] = zeroPosition[0] - 1;
            return newNode;
        }

        if (moveLetter.equals("D")) {
            Node newNode = new Node(this, moveLetter);
            newNode.depth = this.depth + 1;
            newNode.moveSet += moveLetter;
            newNode.board[zeroPosition[0]][zeroPosition[1]] = newNode.board[zeroPosition[0] + 1][zeroPosition[1]];
            newNode.board[zeroPosition[0] + 1][zeroPosition[1]] = 0;
            newNode.zeroPosition[0] = zeroPosition[0] + 1;
            return newNode;
        }

        if (moveLetter.equals("L")) {
            Node newNode = new Node(this, moveLetter);
            newNode.depth = this.depth + 1;
            newNode.moveSet += moveLetter;
            newNode.board[zeroPosition[0]][zeroPosition[1]] = newNode.board[zeroPosition[0]][zeroPosition[1] - 1];
            newNode.board[zeroPosition[0]][zeroPosition[1] - 1] = 0;
            newNode.zeroPosition[1] = zeroPosition[1] - 1;
            return newNode;
        }

        if (moveLetter.equals("R")) {
            Node newNode = new Node(this, moveLetter);
            newNode.depth = this.depth + 1;
            newNode.moveSet += moveLetter;
            newNode.board[zeroPosition[0]][zeroPosition[1]] = newNode.board[zeroPosition[0]][zeroPosition[1] + 1];
            newNode.board[zeroPosition[0]][zeroPosition[1] + 1] = 0;
            newNode.zeroPosition[1] = zeroPosition[1] + 1;
            return newNode;
        }
        return null;
    }

    public void makeNeighbours(String moveOrder) {
        for (int i = 0; i < 4; i++) {
            if (isMovePossible(moveOrder.charAt(i)) && IsNotGoingBack(moveOrder.charAt(i))) {
                neighbours.add(Move(String.valueOf(moveOrder.charAt(i))));
            }
        }
    }

    public boolean IsNotGoingBack(char direction) {
        if ((this.parentMove.equals("L") && direction == 'R') ||
                (this.parentMove.equals("U") && direction == 'D') ||
                (this.parentMove.equals("R") && direction == 'L') ||
                (this.parentMove.equals("D") && direction == 'U')) {
            return false;
        }
        return true;
    }

    public boolean isMovePossible(char direction) {
        if ((zeroPosition[0] == 0 && direction == 'U')
                || (zeroPosition[0] == 3 && direction == 'D')
                || (zeroPosition[1] == 0 && direction == 'L')
                || (zeroPosition[1] == 3 && direction == 'R')) {
            return false;
        }
        return true;
    }


    public int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // copy elements
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    public boolean isGoal(Node s) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.board[i][j] != s.board[i][j])
                    return false;
            }
        }
        return true;
    }

    public void reverseNeighbours() {
        Collections.reverse(neighbours);
    }

    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int calculateHamming() {
        int hamming = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (board[i - 1][j - 1] == (i - 1) * 4 + j || board[i - 1][j - 1] == 0) {
                    continue;
                } else {
                    hamming++;
                }
            }
        }
        hamming += this.depth;
        return hamming;
    }

    public int calculateManhattan() {
        int manhattan = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0 && board[i][j] != i * 4 + j + 1) {
                    int actualValue = board[i][j];
                    int actualRow = i;
                    int actualCol = j;

                    int correctRow = actualValue / 4;
                    int correctCol = actualValue % 4 - 1;
                    manhattan += Math.abs(correctRow - actualRow) + Math.abs(correctCol - actualCol);
                }
            }
        }
        manhattan += this.depth;
        return manhattan;
    }

    @Override
    public int compareTo(Node o) {
        if (this.priority > o.priority) {
            return 1;
        } else if (this.priority < o.priority) {
            return -1;
        } else {
            return 0;
        }
    }
}
