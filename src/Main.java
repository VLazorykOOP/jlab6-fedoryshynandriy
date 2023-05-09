import Task2.*;

import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        System.out.println(" Java Lab #6 ");
        File file = new File("C:\\java\\jlab6-fedoryshynandriy\\src\\matrix.txt");
        try {
            int[][] matrix = Task3.readMatrixFromFile(file);
            System.out.println("Matrix from file:");
            Task3.displayMatrix(matrix);
            int colWithMin = Task3.findMinColumn(matrix);
            System.out.println("Column with min element:"+colWithMin);
            System.out.println("Shifted matrix");
            int[][]shiftedMatrix= Task3.shiftColumns(matrix,colWithMin);
            Task3.displayMatrix(shiftedMatrix);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
