package Criptos;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class Encryptor {

    private static final String ALGORITHM = "AES";

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(256); // Tamaño de clave: 256 bits
        return keyGen.generateKey();
    }

    public static SecretKey convertStringToAESKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, ALGORITHM);
    }

    public static void encryptFile(String inputFile, String outputFile, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        Path inputPath = Paths.get(inputFile);
        byte[] fileBytes = Files.readAllBytes(inputPath);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        Files.write(Paths.get(outputFile), encryptedBytes, StandardOpenOption.CREATE);
    }

    public static void decryptFile(String inputFile, String outputFile, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        Path inputPath = Paths.get(inputFile);
        byte[] encryptedBytes = Files.readAllBytes(inputPath);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        Files.write(Paths.get(outputFile), decryptedBytes, StandardOpenOption.CREATE);
    }
}
