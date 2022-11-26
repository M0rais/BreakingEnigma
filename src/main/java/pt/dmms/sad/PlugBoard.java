package pt.dmms.sad;

import java.util.HashMap;

public class PlugBoard {

    private final HashMap<String, String> plugBoardMap = new HashMap<>();

    public boolean load(String plugboard) {
        plugBoardMap.clear();
        if (plugboard.equalsIgnoreCase("{}")) return true;
        plugboard = plugboard.replace("{", "").replace("}", "");
        try {
            for (String pair : plugboard.split(",")) {
                String[] entry = pair.split(":");
                String keys = entry[0].replace("'", "");
                String values = entry[1].replace("'", "");
                char key = keys.trim().charAt(0);
                char value = values.trim().charAt(0);
                if (Character.isLetter(key) && Character.isLetter(value)) {
                    plugBoardMap.put("" + key, "" + value);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void loadKat(String plugboard) {
        plugBoardMap.clear();
        if (plugboard.equalsIgnoreCase("{}")) return;
        plugboard = plugboard.replace("{", "");
        plugboard = plugboard.replace("}", "");
        String[] keyValuePairs = plugboard.split(",");
        try {
            for (String pair : keyValuePairs) {
                String[] entry = pair.split(":");
                String keys = entry[0].replace("\"", "");
                String values = entry[1].replace("\"", "");
                char key = keys.trim().charAt(0);
                char value = values.trim().charAt(0);
                if (Character.isLetter(key) && Character.isLetter(value)) {
                    plugBoardMap.put("" + key, "" + value);
                }
            }
        } catch (Exception e) {
            System.out.println("sad.PlugBoard incorreta");
        }
    }

    public String translate(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String character : word.split("")) {
            var newCharacter = plugBoardMap.get(character);
            if (newCharacter == null) {
                stringBuilder.append(character);
                continue;
            }
            stringBuilder.append(newCharacter);
        }
        return stringBuilder.toString();
    }

}
