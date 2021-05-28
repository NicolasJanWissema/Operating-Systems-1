import java.io.*;
import java.nio.charset.StandardCharsets;

//operating systems memory management code. Don't know what im doing yet.
public class OS1Assignment {
    //Page table used. can be changed.
    static final long[] pageTable= new long[]{2,4,1,7,3,5,6};
    static final int pageOffset = 5;

    //Begin main method
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
                    )
            {
                //Variables required for translation.
                byte[] buffer = new byte[Byte.SIZE];
                String[] conversionTable = OS1Assignment.convertPageTable(pageTable);
                FileWriter fileWriter = new FileWriter("output.txt");

                //Begin translation
                System.out.println("Beginning translation of addresses.");
                while((inputStream.read(buffer))!=-1)
                {
                    long value = 0;
                    for (int i = 0; i < buffer.length; i++)
                    {
                        value += ((long) buffer[i] & 0xFF) << (8 * i);
                    }
                    //System.out.println("Converting "+OS1Assignment.formatter(Long.toHexString(value)));
                    String binaryStringValue = Long.toBinaryString(value);
                    binaryStringValue = OS1Assignment.fullBinary(binaryStringValue,12);
                    binaryStringValue = conversionTable[(int)Long.parseLong(binaryStringValue.substring(0,5),2)] + binaryStringValue.substring(5,binaryStringValue.length());
                    fileWriter.write("Translated virtual address "+OS1Assignment.formatter(Long.toHexString(value))+" to physical address "+OS1Assignment.formatter(Long.toHexString(Long.parseLong(binaryStringValue,2)))+"\n");
                    //System.out.println("Converted to: "+OS1Assignment.formatter(Long.toHexString(Long.parseLong(binaryStringValue,2)))+"\n");
                }
                fileWriter.close();
                System.out.println("Translation finished. output.txt created displaying results.");
            }
            catch(Exception e){
                e.printStackTrace();
                System.exit(0);
            }
        }
        System.out.println("Program completed.");
    }
    private static String fullBinary(String binaryInput,int length){
        int check =length-binaryInput.length();
        for(int i=0; i<check;i++){
            binaryInput = "0"+binaryInput;
        }
        return(binaryInput);
    }
    private static String[] convertPageTable(long[] table){
        String[] convertedTable = new String[table.length];
        for(int i=0;i<table.length;i++){
            convertedTable[i]=OS1Assignment.fullBinary(Long.toBinaryString(table[i]),pageOffset);
        }
        return (convertedTable);
    }
    private static String formatter(String input){
        int usedHex = input.length()/2+ input.length()%2;
        for(int i=0;i<7-usedHex;i++){
            input = "0"+input;
        }
        return ("0x"+input.toUpperCase());
    }
}