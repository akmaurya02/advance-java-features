package com.java.feature.security.rsa;

import java.io.FileWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAKeyGenerator {

    /**
     * Generates the keys for given size.
     *
     * @param size - Key Size [512|1024|2048]
     * @param path - Path to save the generated files
     */
    public void generateRSAKeys(int size, String path) {

        System.out.println("Generating RSA private and public keys..");
        String privateKeyFile = path + "/rsa_pri.pem";
        String publicKeyFile = path + "/rsa_pub.pem";
        try (Writer out = new FileWriter(privateKeyFile);
             Writer out1 = new FileWriter(publicKeyFile)) {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(size);
            KeyPair keypair = keyGen.genKeyPair();
            out.write("-----BEGIN PRIVATE KEY-----\n");
            out.write(Base64.getEncoder().encodeToString(keypair.getPrivate().getEncoded()));
            out.write("\n-----END PRIVATE KEY-----\n");
            System.out.println("Successfully generated private key, path - " + privateKeyFile);

            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(keypair.getPublic().getEncoded()));
            out1.write("-----BEGIN PUBLIC KEY-----\n");
            out1.write(Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
            out1.write("\n-----END PUBLIC KEY-----\n");
            System.out.println("Successfully generated public key, path - " + publicKeyFile);
        } catch (Exception e) {
            System.out.println("Error in generating RSA keys:: " + e.getMessage());
        }
    }
}
