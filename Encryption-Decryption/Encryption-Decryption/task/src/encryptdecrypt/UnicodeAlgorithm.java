package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;

public class UnicodeAlgorithm implements EncryptionAlgorithmStrategy {
    private StringBuilder sb;

    @Override
    public String encrypt(String plaintext, int key, String outPath) {
        sb = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            sb.append((char) (plaintext.charAt(i) + key));
        }

        return sb.toString();
    }

    @Override
    public String decrypt(String ciphertext, int key, String outPath) {
        sb = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            sb.append((char) (ciphertext.charAt(i) - key));
        }

        return sb.toString();
    }
}
