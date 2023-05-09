package Task2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task3 {
    public static int[][] readMatrixFromFile(File path_to_the_File) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path_to_the_File));
        String line;
        int[][] matrix = null;
        int n = 0;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (matrix == null) {
                n = Integer.parseInt(line);
                matrix = new int[n][n];
            } else {
                String[] tokens = line.split("\\s+");
                if (tokens.length != n) {
                    throw new NumberFormatException("Invalid matrix format in file");
                }
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(tokens[j]);
                }
                i++;
            }
        }
        bufferedReader.close();
        if (matrix == null) {
            throw new NumberFormatException("Empty matrix in file");
        }
        return matrix;
    }

    public static void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int findMinColumn(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int col = -1;
        boolean found = false;
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    col = j;
                    found = true;
                }
            }
        }
        return col;
    }

    public static int[][] shiftColumns(int[][] matrix, int col) {
        int c = 0;
        while (c != col) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length; j++) {
                    int temp = matrix[i][j - 1];
                    matrix[i][j - 1] = matrix[i][j];
                    matrix[i][j] = temp;
                }
            }
            c++;
        }
        return matrix;
    }
}
