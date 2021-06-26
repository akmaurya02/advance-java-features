package com.java.feature.security.aes;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestAesEncryption {

    private static final int IV_SIZE = 16;

    public static void main(String[] args) throws Exception {
        String plainText = "test successful";
        AesKeyGenerator.writeAesKeyInFile("aes_test");
        byte[] keyBytes = AesKeyGenerator.readAesKeyFromFile("aes_test");
        AesEncryption aesEncryption = new AesEncryption();
        byte[] encIvBytes = aesEncryption.getRandomIV();
        System.out.println("plainText: "+plainText);
        System.out.println("key: "+ Base64.encodeBase64String(keyBytes)+" ,length: "+keyBytes.length);
        System.out.println("iv: "+Base64.encodeBase64String(encIvBytes)+" ,length: "+encIvBytes.length);

        String encryptedText = aesEncryption.encrypt(plainText.getBytes(StandardCharsets.UTF_8), keyBytes, encIvBytes);
        System.out.println("encryptedText: "+encryptedText);

        byte[] encryptedTextBytes= Base64.decodeBase64(encryptedText);
        byte[] decIvBytes = Arrays.copyOfRange(encryptedTextBytes, 0, IV_SIZE);
        byte[] cipherTextStr = Arrays.copyOfRange(encryptedTextBytes, decIvBytes.length, encryptedTextBytes.length);
        String decryptedPlainText = aesEncryption.decrypt(cipherTextStr, keyBytes, decIvBytes);
        System.out.println("decryptedPlainText: "+decryptedPlainText);
    }
}
