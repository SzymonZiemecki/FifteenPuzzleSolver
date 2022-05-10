import java.io.*;
import java.util.Scanner;

public class FileManager {

    String readFile;

    public FileManager(String readFile) {
        this.readFile = readFile;
    }

    public int[] readBoardSize() throws FileNotFoundException {
        int[] size = new int[2];
        Scanner scanner = new Scanner(new File(readFile));
        String[] line = scanner.nextLine().trim().split(" ");
        size[0] = Integer.parseInt(line[0]);
        size[1] = Integer.parseInt(line[1]);
        return size;
    }

    public int[][] readBoard() throws IOException {
        String board = "";
        int[] size = readBoardSize();
        int[][] boardInt = new int[size[0]][size[1]];
        Scanner scanner = new Scanner(new File(readFile));
        scanner.nextLine();
        while (scanner.hasNext()) {
            for (int i = 0; i < size[0]; i++) {
                String[] line = scanner.nextLine().trim().split(" ");
                for (int j = 0; j < size[1]; j++) {
                    boardInt[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        return boardInt;
    }

    public void writeToFile(String output, String writeFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
        writer.write(output);
        writer.close();
    }

    public int[][] generateValidBoard() throws FileNotFoundException {
        int[] size = readBoardSize();
        int[][] board = new int[size[0]][size[1]];
        int x=1;
        for(int i = 0 ; i < size[0];i++){
            for(int j = 0 ; j < size[1];j++){
                if (i == size[0]-1 && j == size[1]-1) {
                    board[i][j]=0;
                }
                else {
                    board[i][j] = x;
                    x++;
                }
            }
        }
        return board;
    }


}
