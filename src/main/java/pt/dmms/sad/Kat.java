package pt.dmms.sad;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kat {

    public static void run(String path) {
        Salt salt = new Salt();
        PlugBoard plugBoard = new PlugBoard();
        EnhancedCaesar enhancedCaesar = new EnhancedCaesar();
        int correct = 0;
        int incorrect = 0;
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            String word = null;
            String result = null;
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                while (!line.equals("-")) {
                    if (i == 0) word = line.toUpperCase();
                    else if (i == 2) result = line.toUpperCase();
                    else plugBoard.loadKat(line.toUpperCase());
                    line = myReader.nextLine();
                    i++;
                }
                i = 0;
                boolean shouldBreak = false;
                for (SaltModel saltModel : salt.generateKat(word)) {
                    var plugBoardWord = plugBoard.translate(saltModel.word());
                    int f;
                    int rot = 0;
                    for (f = 0; f <= 26; f++) {
                        for (rot = 0; rot <= 26; rot++) {
                            var newWordFor = enhancedCaesar.translate(plugBoardWord, f, rot);
                            newWordFor = plugBoard.translate(newWordFor);
                            if (Objects.equals(newWordFor, result)) {
                                shouldBreak = true;
                                break;
                            }
                        }
                        if (shouldBreak) break;
                    }
                    if (shouldBreak) {
                        correct++;
                        System.out.println("palavra: " + word);
                        System.out.println("inc: " + f);
                        System.out.println("rot: " + rot);
                        System.out.println("salt: " + saltModel);
                        break;
                    }
                }
                if (!shouldBreak) incorrect++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Kats: " + (correct + incorrect));
        System.out.println("Correct: " + correct);
        System.out.println("Incorrect: " + incorrect);
    }

}