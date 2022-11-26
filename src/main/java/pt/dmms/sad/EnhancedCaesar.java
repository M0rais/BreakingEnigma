package pt.dmms.sad;

public class EnhancedCaesar {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String translate(String word, int f, int rot) {
        StringBuilder newWord = new StringBuilder();
        int index = 0;
        for (String wordChar : word.split("")) {
            var inc = index * f;
            int indexOf = ALPHABET.indexOf(wordChar);
            if (indexOf == -1) continue;
            indexOf = indexOf + rot + inc;
            while (indexOf >= ALPHABET.length()) {
                indexOf = indexOf - ALPHABET.length();
            }
            var newCharacter = ALPHABET.charAt(indexOf);
            newWord.append(newCharacter);
            index++;
        }
        return newWord.toString();
    }

}