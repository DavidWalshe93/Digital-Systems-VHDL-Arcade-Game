import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;

/**
 * Created by David on 04/11/2016.
 */
public class FileIO {

    public FileIO(){

    }
    public void exportBitMap(String input, String output, String spriteName, String size, int sizeInt){
            try {
                FileInputStream is = new FileInputStream(input);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                FileOutputStream os = new FileOutputStream(output+"_Output.txt");
                PrintWriter pw = new PrintWriter(os);
                pw.println("type " + size + " is array ("+ (sizeInt-1) +" downto 0) of unsigned ("+ (sizeInt-1) +" downto 0);     -- only included once");
                pw.println("signal " + spriteName + " : " + size + ";");
                pw.println();
                pw.println();
                String file = br.readLine();
                for (int i = 0; file != null; i++) {
                    file = file.replace(",", " ");
                    file = file.replace(" ", "");
                    file = file.trim();
                    String line = spriteName + "("+i+") <= \"" + file + "\";";
                    pw.println(line);
                    file = br.readLine();
                }
                pw.flush();
                System.out.println("File Creation Successful - " + output+"_Output.txt");
            } catch (Exception ee) {
                //ee.printStackTrace();
                System.out.println("ERROR: File Creation Failed");
                System.out.println("Ensure " + input + " Exists in the inputs folder");
                System.out.println("Ensure ExportSprite.jar and OpenExportSprite.jar are in the same folder as");
                System.out.println("with an inputs and outputs folder");
            }
    }
}
