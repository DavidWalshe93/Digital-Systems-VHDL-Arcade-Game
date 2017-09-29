/**
 * Created by David on 03/11/2016.
 */
public class main {
    public static void main(String[] args) {

        try {
           Runtime.getRuntime().exec("cmd.exe /c start java -jar %CD%\\ExportSprite.jar");
        } catch (Exception ee) {
           ee.printStackTrace();
        }
    }
}
