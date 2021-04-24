package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;

public class ShiftAlgorithm implements EncryptionAlgorithmStrategy {
    private StringBuilder sb;

    @Override
    public String encrypt(String plaintext, int key, String outPath) {
        sb = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (!isLetter(c)) {
                sb.append(c);
            } else if ((c >= 65 && c + key <= 90) || (c >= 97 && c + key <= 122)) {
                sb.append((char) (c + key));
            } else {
                sb.append((char) (c + key - 26));
            }
        }

        return sb.toString();
    }

    @Override
    public String decrypt(String ciphertext, int key, String outPath) {
        sb = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if (!isLetter(c)) {
                sb.append(c);
            } else if ((c <= 90 && c - key >= 65) || (c <= 122 && c - key >= 97)) {
                sb.append((char) (c - key));
            } else {
                sb.append((char) (c - key + 26));
            }
        }

        return sb.toString();
    }

    private boolean isLetter(char c) {
        return c >= 65 && c <= 122;
    }
}
