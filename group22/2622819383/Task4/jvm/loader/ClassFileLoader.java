import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;



public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        for (String path : clzPaths) {
            File f = new File(path + className.replace('.', '/') + ".class");
            if (f.exists() && f.isFile()) 
                System.out.println("f.length(): " + f.length());
                return readBytes(f);            
        }
        return null;				
    }

    private byte[] readBytes(File f) {
        byte[] temp = new byte[1024];
        int hasRead = -1;
        try (
            InputStream in = new FileInputStream(f);
            OutputStream out = new ByteArrayOutputStream())
        {
            while ((hasRead = in.read(temp)) > -1)
                out.write(temp, 0, hasRead);
            return ((ByteArrayOutputStream)out).toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }



    public String getClassPath(){
        StringBuilder s = new StringBuilder();
        for (String str : clzPaths)
            s.append(str).append(";");

        return s.substring(0, s.length() - 1);
    }

    private static String byteToHexString(byte[] codes ){
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<codes.length;i++) {
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if(strHex.length()< 2){
                strHex = "0" + strHex;
            }		
            buffer.append(strHex);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String name = "EmployeeV1";
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath("./");
        byte[] byteCodes = loader.readBinaryCode(name);
        System.out.println("byteCodes.length: " + byteCodes.length);
        byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};
        System.out.println("CAFEBABE: " + byteToHexString(codes));
    }
}













