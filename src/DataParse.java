/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix_Manipulator;

/**
 *
 * @author utkar
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class DataParse {
    
    static char[] operands;
    static char[] operators;
    static Map<String,Matrix> assocation = new HashMap<>();
    
    
    public static void parseOp(String op)
    {
        assocation.clear();
        String[] operators = op.split("[-+*/]");
        String[] operands = op.split("[a-zA-Z]+");
        //System.out.print(Arrays.toString(operators));
        //System.out.print(Arrays.toString(operands));
        for(String temp : operators)
        {
            TestMatrix.prompt(temp);
            assocation.put(temp, new Matrix(TestMatrix.row,TestMatrix.column));
        }
        
    }
    
}
