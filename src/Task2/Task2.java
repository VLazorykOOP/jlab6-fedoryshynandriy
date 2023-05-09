package Task2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.text.JTextComponent;

public class Task2 extends JFrame {

    private JTextField filePath;
    private JButton readMatrix, shiftButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public Task2() {
        super("Task 2");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Input file: "));
        filePath = new JTextField(40);
        inputPanel.add(filePath);
        topPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        readMatrix = new JButton("Read matrix");
        buttonPanel.add(readMatrix);

        shiftButton = new JButton("Shift columns");
        buttonPanel.add(shiftButton);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        JLabel colNumLabel = new JLabel("Number of columns with the minimum element: ");
        infoPanel.add(colNumLabel);
        topPanel.add(infoPanel, BorderLayout.SOUTH);

        readMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[][] matrix = Task3.readMatrixFromFile(new File(filePath.getText()));
                    updateTable(matrix);
                    int minCol = Task3.findMinColumn(matrix);
                    String text = "Number of columns with the minimum element: " + (minCol + 1);
                    colNumLabel.setText(text);
                } catch (IOException | NumberFormatException | MyException ex) {
                    ex.printStackTrace();
                }
            }
        });

        shiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[][] matrix = Task3.readMatrixFromFile(new File(filePath.getText()));
                    int col = Task3.findMinColumn(matrix);
                    matrix = Task3.shiftColumns(matrix, col);
                    updateTable(matrix);
                    int minCol = Task3.findMinColumn(matrix);
                    String text = "Number of columns with the minimum element: " + (minCol + 1);
                    colNumLabel.setText(text);
                } catch (IOException | NumberFormatException | MyException ex) {
                    ex.printStackTrace();
                }
            }
        });

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateTable(int[][] matrix) {
        tableModel.setRowCount(matrix.length);
        tableModel.setColumnCount(matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                tableModel.setValueAt(matrix[i][j], i, j);
            }
        }
    }

    public static void main(String[] args) {
        new Task2();
    }
}
