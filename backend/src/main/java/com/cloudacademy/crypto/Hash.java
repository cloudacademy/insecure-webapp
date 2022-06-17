package com.cloudacademy.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    public static String md5(String input)
    {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            digester.update(input.getBytes());
            byte[] md5Bytes = digester.digest();
            String md5Text = null;
        
            md5Text = bytesToHex(md5Bytes);
        
            return md5Text;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }    
}
