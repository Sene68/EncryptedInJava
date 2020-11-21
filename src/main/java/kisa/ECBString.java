package kisa;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ECBString {

    private static final byte pbUserKey[]  = {(byte)0x71, (byte)0x6c, (byte)0x64, (byte)0x34, (byte)0x6b, (byte)0x66, (byte)0x66,
            (byte)0x6b, (byte)0x61, (byte)0x6c, (byte)0x40, (byte)0x31, (byte)0x65, (byte)0x6b, (byte)0x50, (byte)0x7a, (byte)0x6b, (byte)0x3d};

    private static final String charset = "utf-8";

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    private static final String str = "sene68";

    public static void main(String[] args) {

        try {
            //한글 깨짐으로 인해 문자열을 Base64로 변경
            byte[] strToBase64 = encoder.encode(str.getBytes(charset));

            //ECB 암호화
            byte[] encryptedStr = encrypt(strToBase64);

            //Base64 Encoding
            byte[] encBytes = encoder.encode(encryptedStr);

            String encResult = new String(encBytes, charset);
            System.out.println("encrypted Str : " + encResult);

            byte[] decBytes = decoder.decode(encResult.getBytes());

            String decryptedStr = decrypt(decBytes);

            byte[] originalStr = decoder.decode(decryptedStr.getBytes());

            String decResult = new String(originalStr, charset);
            System.out.println("decrypted Str : " + decResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static byte[] encrypt(byte[] str) {
        byte[] enc = new byte[50];

        //암호화 함수 호출
        enc = KISA_SEED_ECB.SEED_ECB_Encrypt(pbUserKey, str, 0 , str.length);

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encArray = encoder.encode(enc);
        try {
            System.out.println(new String(encArray, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return enc;
    }

    public static String decrypt(byte[] str) {

        String result = "";
        byte[] dec = new byte[16];

        //복호화 함수 호출
        dec = KISA_SEED_ECB.SEED_ECB_Decrypt(pbUserKey, str, 0, 16);
        result = new String(dec, StandardCharsets.UTF_8);

        System.out.println("decrypt Result = " + result);
        return result;

    }
}
