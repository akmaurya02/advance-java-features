package com.java.feature.security.aes;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class AesKeyGenerator {

    private static final int ITERATIONS = 65536;
    private static final int KEY_SIZE = 256;
    private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA256";

    private static byte[] getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] getAesKey(String appId) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
        PBEKeySpec spec = new PBEKeySpec(DigestUtils.sha256Hex(appId).toCharArray(), getSalt(), ITERATIONS, KEY_SIZE);
        SecretKey secretKey = skf.generateSecret(spec);
        return secretKey.getEncoded();
    }

    public static void writeAesKeyInFile(String appId)  {
        String file = "conf/" + appId + ".key";
        try (FileWriter fileWriter = new FileWriter(file)) {
            byte[] aesKey = getAesKey(appId);
            fileWriter.write(Base64.encodeBase64String(aesKey));
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readAesKeyFromFile(String appId)  {
        String file = "conf/" + appId + ".key";
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while(line!= null)
            {
                sb.append(line);
                line = reader.readLine();
            }
            return Base64.decodeBase64(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
