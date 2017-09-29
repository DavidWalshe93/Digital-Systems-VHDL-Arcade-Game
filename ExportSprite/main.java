
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

/**
 * Created by David on 03/11/2016.
 */
public class main {
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        FileIO io = new FileIO();
        String[] sizeString = {"_8x8", "_16x16", "_32x32", "_64x64", "_128x128"};
        String[] colourString = {"_RED", "_GREEN", "_BLUE"};
        String[] arraySizeStrings = {"array_type_8x8", "array_type_16x16", "array_type_32x32", "array_type_64x64", "array_type_128x128"};
        String[] suffix8Bit = new String[3];
        String[] suffix16Bit = new String[3];
        String[] suffix32Bit = new String[3];
        String[] suffix64Bit = new String[3];
        String[] suffix128Bit = new String[3];
        String[] FullFilePathInput = new String[3];
        String[] FullFilePathOutput = new String[3];
        String[] colourTag = {"_R", "_G", "_B", "_W"};
        String sizeStr;
        int sizeInt = 0;
        String fileDir = System.getProperty("user.dir");    //"C:\\SpriteMaker\\";
        String fileDirOut = "\\Outputs\\";
        String fileDirIn = "\\Inputs\\";

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                switch (i) {
                    case 0:
                        suffix8Bit[j] = sizeString[i] + colourString[j];
                        break;
                    case 1:
                        suffix16Bit[j] = sizeString[i] + colourString[j];
                        break;
                    case 2:
                        suffix32Bit[j] = sizeString[i] + colourString[j];
                        break;
                    case 3:
                        suffix64Bit[j] = sizeString[i] + colourString[j];
                        break;
                    case 4:
                        suffix128Bit[j] = sizeString[i] + colourString[j];
                        break;
                    default:
                        System.out.println("ERROR: Naming convention out of bounds");
                        break;
                }
            }

        }
        String extensionSuffix = ".csv";
        do {
            System.out.print("Enter the size of the Bitmap to convert (8,16,32,64,128,?): ");
            sizeStr = sin.nextLine();

            if (sizeStr.equals("8") || sizeStr.equals("16") || sizeStr.equals("32") || sizeStr.equals("64") || sizeStr.equals("128")) {
                sizeInt = Integer.parseInt(sizeStr);
            }
            else if(sizeStr.equals("?")) {
                System.out.println("VHDL Sprite Maker");
                System.out.println("Digital Systems / SoC Design and Verification");
                System.out.println("Designed by David Walshe - 11/2016");
                System.out.println();
                System.out.println();
            } else {
                System.out.println("ERROR: 8, 16, 32, 64 or 128 was not entered");
                System.out.println("Try again");
                System.out.println();
            }
        } while (sizeInt != 8 && sizeInt != 16 && sizeInt != 32 && sizeInt != 64 && sizeInt != 128);

        System.out.print("Enter File Name (Exclude _*x*_*** suffix): ");
        String fileName = sin.nextLine();
        System.out.print("VHDL Array Name: ");
        String spriteName = sin.nextLine();

        System.out.println("File Dir: " + fileDir);
        switch (sizeInt) {
            case 8:
                for (int i = 0; i < 3; i++) {
                    FullFilePathInput[i] = fileDir + fileDirIn + fileName + suffix8Bit[i] + extensionSuffix;
                    FullFilePathOutput[i] = fileDir + fileDirOut + fileName + suffix8Bit[i] + extensionSuffix;
                    io.exportBitMap(FullFilePathInput[i], FullFilePathOutput[i], (spriteName+colourTag[i]), arraySizeStrings[0], sizeInt);
                }
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + suffix8Bit[0] + extensionSuffix;
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + sizeString[0] + extensionSuffix;
                io.exportBitMap(FullFilePathInput[0], FullFilePathOutput[0], spriteName+colourTag[3], arraySizeStrings[0], sizeInt);
                break;
            case 16:
                for (int i = 0; i < 3; i++) {
                    FullFilePathInput[i] = fileDir + fileDirIn + fileName + suffix16Bit[i] + extensionSuffix;
                    FullFilePathOutput[i] = fileDir + fileDirOut + fileName + suffix16Bit[i] + extensionSuffix;
                    io.exportBitMap(FullFilePathInput[i], FullFilePathOutput[i], (spriteName+colourTag[i]), arraySizeStrings[1], sizeInt);
                }
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + suffix16Bit[0] + extensionSuffix;
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + sizeString[1] + extensionSuffix;
                io.exportBitMap(FullFilePathInput[0], FullFilePathOutput[0], spriteName+colourTag[3], arraySizeStrings[1], sizeInt);
                break;
            case 32:
                for (int i = 0; i < 3; i++) {
                    FullFilePathInput[i] = fileDir + fileDirIn + fileName + suffix32Bit[i] + extensionSuffix;
                    FullFilePathOutput[i] = fileDir + fileDirOut + fileName + suffix32Bit[i] + extensionSuffix;
                    io.exportBitMap(FullFilePathInput[i], FullFilePathOutput[i], (spriteName+colourTag[i]), arraySizeStrings[2],  sizeInt);
                }
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + suffix32Bit[0] + extensionSuffix;
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + sizeString[2] + extensionSuffix;
                io.exportBitMap(FullFilePathInput[0], FullFilePathOutput[0], spriteName+colourTag[3], arraySizeStrings[2], sizeInt);
                break;
            case 64:
                for (int i = 0; i < 3; i++) {
                    FullFilePathInput[i] = fileDir + fileDirIn + fileName + suffix64Bit[i] + extensionSuffix;
                    FullFilePathOutput[i] = fileDir + fileDirOut + fileName + suffix64Bit[i] + extensionSuffix;
                    io.exportBitMap(FullFilePathInput[i], FullFilePathOutput[i], (spriteName+colourTag[i]), arraySizeStrings[3],  sizeInt);
                }
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + suffix64Bit[0] + extensionSuffix;
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + sizeString[3] + extensionSuffix;
                io.exportBitMap(FullFilePathInput[0], FullFilePathOutput[0], spriteName+colourTag[3], arraySizeStrings[3],  sizeInt);
                break;
            case 128:
                for (int i = 0; i < 3; i++) {
                    FullFilePathInput[i] = fileDir + fileDirIn + fileName + suffix128Bit[i] + extensionSuffix;
                    FullFilePathOutput[i] = fileDir + fileDirOut + fileName + suffix128Bit[i] + extensionSuffix;
                    io.exportBitMap(FullFilePathInput[i], FullFilePathOutput[i], (spriteName+colourTag[i]), arraySizeStrings[4], sizeInt);
                }
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + suffix128Bit[0] + extensionSuffix;
                FullFilePathOutput[0] = fileDir + fileDirOut + fileName + sizeString[4] + extensionSuffix;
                io.exportBitMap(FullFilePathInput[0], FullFilePathOutput[0], spriteName+colourTag[3], arraySizeStrings[4], sizeInt);
                break;
            default:
                System.out.println("ERROR: File path generation failed");
                break;
        }
        try {
            System.out.println("ENTER to Exit");
            int a = System.in.read();
        }catch(Exception ee){
            ee.printStackTrace();
        }
    }
}
