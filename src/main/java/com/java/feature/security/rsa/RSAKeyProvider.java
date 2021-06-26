package com.java.feature.security.rsa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RSAKeyProvider {

    private static final String BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----";
    private static final String END_PRIVATE_KEY = "-----END PRIVATE KEY-----";
    private static final String BEGIN_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----";
    private static final String END_PUBLIC_KEY = "-----END PUBLIC KEY-----";
    private static final String PRIVATE_KEY = "rsa-privateKey";
    private static final String PUBLIC_KEY = "rsa-publicKey";
    private static final String CONF = "conf";

    private final ConcurrentMap<String, String> rsaKeys = new ConcurrentHashMap<>();

    public String getServerRsaPrivateKey() throws IOException {
        String privateKey;
        if (rsaKeys.containsKey(PRIVATE_KEY)) {
            privateKey = rsaKeys.get(PRIVATE_KEY);
        } else {
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get(CONF + "/rsa_pri.pem"));
            String temp = new String(privateKeyBytes);
            String privateKeyPEM = temp.replace(BEGIN_PRIVATE_KEY, "");
            privateKey = privateKeyPEM.replace(END_PRIVATE_KEY, "");
            rsaKeys.put(PRIVATE_KEY, privateKey);
        }
        return privateKey;
    }

    public String getServerRsaPublicKey() throws IOException {
        String publicKey;
        if (rsaKeys.containsKey(PUBLIC_KEY)) {
            publicKey = rsaKeys.get(PUBLIC_KEY);
        } else {
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(CONF + "/rsa_pub.pem"));
            String temp = new String(publicKeyBytes);
            String publicKeyPEM = temp.replace(BEGIN_PUBLIC_KEY, "");
            publicKey = publicKeyPEM.replace(END_PUBLIC_KEY, "");
            rsaKeys.put(PUBLIC_KEY, publicKey);
        }
        return publicKey;
    }
}
