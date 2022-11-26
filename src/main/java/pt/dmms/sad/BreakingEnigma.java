package pt.dmms.sad;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BreakingEnigma {

    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();

        if (args.length < 3) {
            logger.log(Level.WARNING, "3 arguments were expected and only " + args.length + " were found!");
            return;
        }

        String hash = args[0];
        if (!hash.matches("^[0-9a-fA-F]{128}$")) {
            logger.log(Level.WARNING, "The hash that was provided does not meet the standards. It should be in SHA512!");
            return;
        }

        String plugBoardArgument = args[1];
        PlugBoard plugBoard = new PlugBoard();
        if (!plugBoard.load(plugBoardArgument)) {
            logger.log(Level.WARNING, "The plugboard that was provided does not meet the standards!");
            return;
        }

        String wordListPath = args[2];
        File file = new File(wordListPath);
        if (!file.exists()) {
            logger.log(Level.WARNING, "The wordlist couldn't be found!");
            return;
        }

        Salt salt = new Salt();
        EnhancedCaesar enhancedCaesar = new EnhancedCaesar();
        WordList wordList = new WordList();

        wordList.find(wordListPath).parallelStream().forEach(word -> {
            System.out.println("Current word: " + word);
            for (SaltModel saltModel : salt.generate(word)) {
                String saltWord = saltModel.word();
                String plugBoardWord = plugBoard.translate(saltWord);
                for (int rot = 0; rot <= 26; rot++) {
                    for (int F = 0; F <= 26; F++) {
                        String newWord = enhancedCaesar.translate(plugBoardWord, F, rot);
                        String plugBoardedWord = plugBoard.translate(newWord);
                        String hashed = SHA512.calculate(plugBoardedWord);
                        if (hashed.equalsIgnoreCase(hash)) {
                            System.out.println("Word: " + word);
                            System.out.println("Salt: " + saltModel);
                            System.out.println("Rotores: " + rot);
                            System.out.println("F: " + F);
                            System.out.println("Password: " + plugBoardedWord);
                            System.exit(200);
                        }
                    }
                }
            }
        });
    }

}