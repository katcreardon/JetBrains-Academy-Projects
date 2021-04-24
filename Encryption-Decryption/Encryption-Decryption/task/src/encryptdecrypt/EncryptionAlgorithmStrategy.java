package encryptdecrypt;

public interface EncryptionAlgorithmStrategy {
    public String encrypt(String plaintext, int key, String outPath);
    public String decrypt(String plaintext, int key, String outPath);
}
