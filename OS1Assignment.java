import java.io.*;

//operating systems memory management code. Don't know what im doing yet.
public class OS1Assignment {
    //Page table used. can be changed.
    static final long[] PageTable= new long[]{2,4,1,7,3,5,6};

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

                byte[] buffer = new byte[Byte.SIZE];
                Long.toHexString(Long.parseLong(Long.toBinaryString(2),2));
                while((inputStream.read(buffer))!=-1)
                {
                    long value = 0;
                    for (int i = 0; i < buffer.length; i++)
                    {
                        value += ((long) buffer[i] & 0xFF) << (8 * i);
                    }
                    System.out.println(Long.toHexString(value)+":");
                    String binaryStringValue = Long.toBinaryString(value);
                    binaryStringValue = OS1Assignment.fullBinary(binaryStringValue,12);


                    System.out.println(binaryStringValue);
                    System.out.println(binaryStringValue.substring(0,4));
                    if(binaryStringValue.substring(0,5).compareTo("00000")==0){
                        binaryStringValue = "00010"+binaryStringValue.substring(5,binaryStringValue.length());
                    }
                    if(binaryStringValue.substring(0,5).compareTo("00100")==0){
                        binaryStringValue = "00011"+binaryStringValue.substring(5,binaryStringValue.length());
                    }
                    System.out.println(binaryStringValue);
                    System.out.println(Long.toHexString(Long.parseLong(binaryStringValue,2)));
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
    private static String fullBinary(String binaryInput,int length){
        int check =length-binaryInput.length();
        for(int i=0; i<check;i++){
            binaryInput = "0"+binaryInput;
        }
        return(binaryInput);
    }
}