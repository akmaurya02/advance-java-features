package com.java.feature.security.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class RSAEncryption {

    private static final String KEY_ALGORITHM = "RSA";
    //private static final String ENCRYPTION_ALGORITHM = "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
    private static final String ENCRYPTION_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final String SIGN_ALGORITHM = "SHA256withRSA";

    public byte[] encrypt(byte[] plainTextBytes, byte[] publicKeyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey fileGeneratedPublicKey = keyFactory.generatePublic(spec);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) (fileGeneratedPublicKey);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        return cipher.doFinal(plainTextBytes);
    }

    public byte[] decrypt(byte[] encryptedTextBytes, byte[] privateKeyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        PrivateKey privateKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedTextBytes);
    }

    public byte[] generateSignature(byte[] encryptedTextBytes, byte[] privateKeyBytes) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidKeySpecException {
        PrivateKey privateKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        Signature privateSignature = Signature.getInstance(SIGN_ALGORITHM);
        privateSignature.initSign(privateKey);
        privateSignature.update(encryptedTextBytes);
        return privateSignature.sign();
    }

    public boolean verifySignature(byte[] encryptedTextBytes, byte[] signature, byte[] publicKeyBytes) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey fileGeneratedPublicKey = keyFactory.generatePublic(spec);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) (fileGeneratedPublicKey);
        Signature publicSignature = Signature.getInstance(SIGN_ALGORITHM);
        publicSignature.initVerify(rsaPublicKey);
        publicSignature.update(encryptedTextBytes);
        return publicSignature.verify(signature);
    }
}
