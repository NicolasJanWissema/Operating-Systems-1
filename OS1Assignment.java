import java.io.*;
import java.util.Scanner;
//operating systems memory management code. Don't know what im doing yet.
public class OS1Assignment {
    public static void main(String[] args){
        //Check input argument.
        if(args.length!=1){
            System.out.println("Argument syntax error. program takes one input parameter <input sequence filename>.");
            System.out.println("ending program.");
            System.exit(0);
        }
        else{
            System.out.println("input filename: " +args[0]);
            //Read file.
            try{
                BufferedReader br=new BufferedReader(new FileReader(args[0]));
                int r;
                while((r=br.read())!=-1)
                {
                    System.out.print((char)r);
                }
                System.out.println();
            }
            catch(Exception e){
                e.printStackTrace();
                System.exit(0);
            }
        }
        System.out.println("Program completed.");
    }
}