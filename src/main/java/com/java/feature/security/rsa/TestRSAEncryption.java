package com.java.feature.security.rsa;

import org.apache.commons.codec.binary.Base64;

public class TestRSAEncryption {

    public static void main(String[] args) {
        try {
            // To generate RSA private & public keys
            RSAKeyGenerator rsaKeyGenerator = new RSAKeyGenerator();
            rsaKeyGenerator.generateRSAKeys(2048, "conf");

            RSAKeyProvider rsaKeyProvider = new RSAKeyProvider();
            String privateKey = rsaKeyProvider.getServerRsaPrivateKey();
            String publicKey = rsaKeyProvider.getServerRsaPublicKey();

            String plainText = "RSA test successful";
            System.out.println("Text byte length:: "+plainText.getBytes().length);
            System.out.println("Plain text:: "+plainText);

            RSAEncryption rsaEncryption = new RSAEncryption();
            byte[] encrTextBytes = rsaEncryption.encrypt(plainText.getBytes(), Base64.decodeBase64(publicKey));
            String encrTextStr = Base64.encodeBase64String(encrTextBytes);
            System.out.println("Encrypted text:: "+encrTextStr);

            byte[] decrTextBytes = rsaEncryption.decrypt(Base64.decodeBase64(encrTextStr), Base64.decodeBase64(privateKey));
            String decrText = new String(decrTextBytes, "UTF-8");
            System.out.println("Decrypted text:: "+decrText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
