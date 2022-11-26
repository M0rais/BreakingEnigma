package pt.dmms.sad;

public record SaltModel(String withoutSalt, String word, String salt, boolean mode) {
}