package pt.dmms.sad;

import java.util.ArrayList;
import java.util.List;

public class Salt {

    private final String SALT_ALPHABET = "ABCDEFGHIJKLM";

    public List<SaltModel> generate(String word) {
        List<SaltModel> saltModels = new ArrayList<>();
        for (int i = 0; i < SALT_ALPHABET.length(); i++) {
            for (int j = 0; j < SALT_ALPHABET.length(); j++) {
                String salt = SALT_ALPHABET.charAt(i) + "" + SALT_ALPHABET.charAt(j);
                saltModels.add(new SaltModel(word, word + salt, salt, true));
                saltModels.add(new SaltModel(word, salt + word, salt, false));
            }
        }
        return saltModels;
    }

    public List<SaltModel> generateKat(String word) {
        List<SaltModel> saltModels = new ArrayList<>();
        for (int i = 65; i <= 90; i++) {
            String saltA = "" + ((char) i);
            String saltB;
            for (int j = 65; j <= 90; j++) {
                saltB = "" + ((char) j);
                saltModels.add(new SaltModel(word, word + saltA + saltB, saltA + saltB, true));
                saltModels.add(new SaltModel(word, saltA + saltB + word, saltA + saltB, false));
            }
        }
        return saltModels;
    }


}
