/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* This the is the test class where the main function is present. Helper functions of this class are present in Matrix.java*/


import java.util.Scanner;
public class TestMatrix
{
	static int row,column;

	public static void main(String[] args) // main method
	{
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to the matrix calculator.");
            System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n","Please select one of the following options.","1- Adjoint","2- Co-Factor","3- Determinant", "4- Inverse", "5- Solve Expression", "Press ctrl+z to exit");
            //Input for switch case. whic is used to select the opration to perform
            while(input.hasNext())
            {
                Matrix temp = null;
                switch(input.nextInt())
                {
                    
                    case 1:
                        //find adjoint of a matrix
                        
                        prompt(); // function for necessary prompts and intializes static variable "row" and "column".
                        temp = new Matrix(row,column); // initializes the matrix temp with provided dimensions.
                        Matrix.print(Matrix.adjoint(temp.getData(),temp.getRow())); //helper function adjoint inside helper function print.
                        break;
                        
                    case 2:
                        //find Co-factor of a matrix
                        prompt(); 
                        temp = new Matrix(row,column);
                        double[][] aux = new double[row][column];
                        double[][] cofactor = new double[row][column];
                        int sign = 1;
                        for(int i = 0; i < row; i++)
                        {
                            for(int j=0; j < column; j++)
                            {
                                Matrix.coFactor(temp.getData(), aux, i, j, row);
                                cofactor[i][j] = sign * ((aux[0][0]*aux[1][1]) - (aux[0][1]*aux[1][0]) );
                                sign = -sign;
                            }
                        }
                        Matrix.print(cofactor);
                        break;
                        
                    case 3:
                        prompt();
                        temp = new Matrix(row,column);
                        System.out.print("The determinant is : ");
                        System.out.println(Matrix.findDeterminant(row, temp.getData(), row)); 
                        
                    case 4:
                        prompt();
                        temp = new Matrix(row,column);
                        System.out.println("The matrix is : ");
                        Matrix.print(Matrix.findInverse(Matrix.findDeterminant(row, temp.getData(), row) , Matrix.adjoint(temp.getData(),temp.getRow() ) ) );
                        break;
                        
                    case 5:
                        System.out.println("please enter the equation.");
                        input.nextLine(); //clear buffer.
                        DataParse.parseOp(input.nextLine());
                }
            }
            
	}
	public static void prompt(String name)
	{
            Scanner input = new Scanner(System.in);
            System.out.println("\nEnter the order of Matrix '" +name+ "' in the form of Row and Colum");
            System.out.print("Row = ");
            row = input.nextInt();
            if (row < 0) {
                while (row < 0) {
                    System.out.println("invalid value try again.");
                    row = input.nextInt();
                }
            }
                    
            System.out.print("Column = ");
            column = input.nextInt();
            if (column < 0) {
                while (column < 0) {
                    System.out.println("invalid value try again.");
                    column = input.nextInt();
                }
            }
	}
        
        public static void prompt()
	{
            Scanner input = new Scanner(System.in);
            System.out.println("\nEnter the order of Matrix in the form of Row and Colum");
            System.out.print("Row = ");
            row = input.nextInt();
            if (row < 0) {
                while (row < 0) {
                    System.out.println("invalid value try again.");
                    row = input.nextInt();
                }
            }
                    
            System.out.print("Column = ");
            column = input.nextInt();
            if (column < 0) {
                while (column < 0) {
                    System.out.println("invalid value try again.");
                    column = input.nextInt();
                }
            }
	}
		
}
