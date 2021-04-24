package encryptdecrypt;

public class EncryptionContext {
    private EncryptionAlgorithmStrategy strategy;

    public EncryptionContext(EncryptionAlgorithmStrategy strategy) {
        this.strategy = strategy;
    }

    public String executeEncryptStrategy(String plaintext, int key, String outPath) {
        return strategy.encrypt(plaintext, key, outPath);
    }

    public String executeDecryptStrategy(String ciphertext, int key, String outPath) {
        return strategy.decrypt(ciphertext, key, outPath);
    }
}
