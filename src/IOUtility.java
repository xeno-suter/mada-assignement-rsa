import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOUtility {
    public static String FILE_NAME_PUBLIC_KEY = "pk.txt";
    public static String FILE_NAME_PRIVATE_KEY = "sk.txt";

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

    private String formatKeyPair(BigInteger n, BigInteger k) {
        return "(" + n.toString() + "," + k.toString() + ")";
    }
}
