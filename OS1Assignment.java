import java.io.*;

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
            try(
                    FileInputStream inputStream = new FileInputStream(args[0]);
                    //OutputStream outputStream = new FileOutputStream(outputFile);
                    )
            {
                byte[] buffer = new byte[Byte.SIZE];
                inputStream.read(buffer);
                System.out.print(buffer);
                while((inputStream.read(buffer))!=-1)
                {
                    for (int i=0;i< buffer.length;i++){
                        System.out.print(buffer[i]& 0xFF);
                        //System.out.print(toBitString(buffer[i]));
                    }
                    System.out.println();
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
    public String toBits(final byte val) {
        final StringBuilder result = new StringBuilder();

        for (int i=0; i<8; i++) {
            result.append((int)(val >> (8-(i+1)) & 0x0001));
        }

        return result.toString();
    }
    public static String toBitString(final byte val) {
        return String.format("%8s", Integer.toBinaryString(val & 0xFF))
                .replace(' ', '0');
    }
}