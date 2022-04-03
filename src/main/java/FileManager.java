import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    String readFile;

    public FileManager(String readFile) {
        this.readFile = readFile;
    }

    public int[][] readBoard() throws IOException {
        String board = "";
        int[][] boardInt = new int[4][4];
        Scanner scanner = new Scanner(new File(readFile));
        scanner.nextLine();
        while (scanner.hasNext()) {
            for (int i = 0; i < 4; i++) {
                String[] line = scanner.nextLine().trim().split(" ");
                for (int j = 0; j < 4; j++) {
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


}
