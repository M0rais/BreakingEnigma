package pt.dmms.sad;

import java.security.MessageDigest;

public class SHA512 {

    /**
     * Método para calcular o SHA512
     * @param wordForHashing
     * @return
     * @see <a href="https://stackoverflow.com/a/33085670">how-to-hash-a-password-with-sha-512-in-java</a>
     */
    public static String calculate(String wordForHashing) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] data = md.digest(wordForHashing.getBytes());
            for (byte datum : data) {
                sb.append(Integer.toString((datum & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sb.toString();
    }

}