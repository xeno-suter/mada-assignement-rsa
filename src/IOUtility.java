import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOUtility {
    public static String FILE_NAME_PUBLIC_KEY = "pk.txt";
    public static String FILE_NAME_PRIVATE_KEY = "sk.txt";
    public static String FILE_NAME_ENCRYPTED_MESSAGE = "cipher.txt";

    public void writePublicKey(BigInteger n, BigInteger d) {
        writeKey(n, d, FILE_NAME_PUBLIC_KEY);
    }

    public void writePrivateKey(BigInteger n, BigInteger e) {
        writeKey(n, e, FILE_NAME_PRIVATE_KEY);
    }

    private void writeKey(BigInteger n, BigInteger k, String fileName) {
        var path = Path.of(fileName);
        try {
            Files.write(path, formatKeyPair(n, k).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeEncryptedMessage(BigInteger[] encrypted) {
        var path = Path.of(FILE_NAME_ENCRYPTED_MESSAGE);
        try {
            var content = new StringBuilder();
            for (int i = 0; i < encrypted.length; i++) {
                content.append(encrypted[i]);
                if (i < encrypted.length - 1) {
                    content.append(",");
                }
            }
            Files.write(path, content.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatKeyPair(BigInteger n, BigInteger k) {
        return "(" + n.toString() + "," + k.toString() + ")";
    }

    // Read the text file and return the content as a byte array (ASCII values)
    public byte[] readTextFile(String fileName) {
        try {
            return Files.readAllBytes(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
