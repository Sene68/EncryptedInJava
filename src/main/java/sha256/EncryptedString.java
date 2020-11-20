package sha256;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptedString {
    private static String msg = "sene";

    public static void main(String[] args) {
        try {
            System.out.println(sha256(msg));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String sha256(String msg) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(msg.getBytes());

        return byteToHexString(md.digest());

    }

    public static String byteToHexString(byte[] data) {

        StringBuilder sb = new StringBuilder();

        for(byte b : data) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
}
