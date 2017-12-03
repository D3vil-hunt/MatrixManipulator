
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

public class Matrix {
    //					Instance variables

    private final int row, column;
    private final double[][] data;
    private final String order;
    private final boolean nature;
    private final String category;

    //					Constructor
    Matrix(int row, int column) {
        this.row = row;
        this.column = column;
        nature = row == column;
        category = "EXTERNAL";
        order = Integer.toString(row) + "X" + Integer.toString(column);
        data = new double[row][column];
        Scanner input = new Scanner(System.in);
        System.out.println("");
        for (int i = 0; i < row; i++) {
            System.out.printf("Enter the elements of row number %d of matrix. Seperated by ',' eg: 1,2,3 : ", (i + 1));
            String usr_input = input.nextLine();
            String[] row_input = usr_input.trim().split(",");
            for (int j = 0; j < column; j++) {
                data[i][j] = Integer.parseInt(row_input[j]);
            }
        }
    }

    Matrix(double[][] data) {
        row = data.length;
        column = data[0].length;
        this.data = data;
        order = Integer.toString(row) + "X" + Integer.toString(column);
        category = "INTERNAL";
        nature = row == column;
    }

//				Operations
    public static Matrix sum(Matrix a, Matrix b) {
        if (a.nature == b.nature || (a.row == b.row && a.column == b.column)) {
            System.out.println();
            double sum[][] = new double[a.row][a.column];
            //System.out.println(sum.length);
            for (int i = 0; i < a.row; ++i) {
                for (int j = 0; j < a.column; j++) {
                    sum[i][j] = a.data[i][j] + b.data[i][j];
                }
                System.out.println("");
            }
            return (new Matrix(sum));
        }
        return null;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        if (a.row == b.row && a.column == b.column) {
            System.out.println("");
            double diff[][] = new double[a.row][a.column];
            for (int i = 0; i < a.row; ++i) {
                for (int j = 0; j < a.column; j++) {
                    diff[i][j] = a.data[i][j] - b.data[i][j];
                    System.out.print(diff[i][j]);
                }
                System.out.println("");
            }
            return (new Matrix(diff));
        }
        return null;
    }

    public static double findDeterminant(int len, double[][] data, int cur_order) {
        int determinant = 0;

        if (cur_order == 1) {
            return data[0][0];
        }

        double[][] temp = new double[len][len];
        int sign = 1;

        for (int ex_column = 0; ex_column < cur_order; ex_column++) {
            coFactor(data, temp, 0, ex_column, cur_order);
            determinant += sign * data[0][ex_column] * findDeterminant(len, temp, cur_order - 1);
            sign = -sign;
        }
        return determinant;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix result;
        double[][] temp = new double[a.row][b.column];
        if (a.column == b.row) {
            for (int i = 0; i < a.row; i++) {
                for (int j = 0; j < b.column; j++) {
                    for (int k = 0; k < a.column; k++) {
                        temp[i][j] += a.data[i][k] * b.data[k][j];
                    }
                }
            }
            return result = new Matrix(temp);
        }
        return null;
    }

    public static double[][] findInverse(double det, double[][] adj) {
        double[][] temp = new double[adj.length][adj.length];

        double determinant = (1 / (double) det);
        //System.out.println("This is determinant"+determinant);

        for (int row = 0; row < adj.length; row++) {
            for (int column = 0; column < adj.length; column++) {
                try {
                    temp[row][column] = determinant * adj[row][column];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("In class Matrix module method findInverse 1n 115" + e);
                }
            }
        }
        return temp;
    }

    public static Matrix divide(Matrix a, Matrix b) {
        Matrix result;
        if (b.nature == true) {
            double det = findDeterminant(b.data.length, b.data, b.data.length);
            System.out.println(det);
            if (0 != det) {
                double[][] adj = adjoint(b.data, b.data.length);
                System.out.println("Adjoint is");
                Matrix.print(adj);
                double[][] inverse = findInverse(det, adj);
                System.out.println("Inverse is");
                Matrix.print(inverse);
                Matrix temp = new Matrix(inverse);
                return (result = multiply(a, temp));
            }
        }
        return result = null;
    }

    //				Auxilary Methods
    public static void print(Matrix a) {
        System.out.println("");
        for (int i = 0; i < a.row; ++i) {
            for (int j = 0; j < a.column; j++) {
                System.out.printf("%15.2f", a.data[i][j]);
            }
            System.out.println("");
        }
    }

    public static void print(double[][] data) {
        System.out.println("");
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.printf("%5.2f\t", data[i][j]);
            }
            System.out.println("");
        }
    }

    public static void coFactor(double[][] data, double[][] temp, int ex_row, int ex_column, int cur_order) {
        int i = 0;
        int j = 0;

        for (int row = 0; row < cur_order; row++) {
            for (int column = 0; column < cur_order; column++) {
                if (row != ex_row && column != ex_column) {
                    temp[i][j++] = data[row][column];
                }
                if (j == (cur_order - 1)) {
                    i++;
                    j = 0;
                }
            }
        }
        //print(temp);
    }
    
    public static double[][] adjoint(double[][] data, int cur_order) {
        if (cur_order == 1) {
            data[0][0] = 1;
        }
        double adj[][] = new double[cur_order][cur_order];
        double temp[][] = new double[cur_order][cur_order];
        int sign;
        for (int row = 0; row < cur_order; row++) {
            for (int column = 0; column < cur_order; column++) {
                coFactor(data, temp, row, column, cur_order);
                sign = ((row + column) % 2 == 0) ? 1 : -1;
                adj[column][row] = (sign) * (findDeterminant(cur_order, temp, cur_order - 1));
            }
        }
        return adj;
    }

    public double[][] getData() {
        return data;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    

}//end of class Matrix
