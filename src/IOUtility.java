import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOUtility {
    public static String FILE_NAME_PUBLIC_KEY = "pk.txt";
    public static String FILE_NAME_PRIVATE_KEY = "sk.txt";
    public static String FILE_NAME_ENCRYPTED_MESSAGE = "cipher.txt";
    public static String FILE_NAME_DECRYPTED_MESSAGE = "text-d.txt";

    private final Path publicKeyPath = Path.of(FILE_NAME_PUBLIC_KEY);
    private final Path privateKeyPath = Path.of(FILE_NAME_PRIVATE_KEY);
    private final Path encryptedMessagePath = Path.of(FILE_NAME_ENCRYPTED_MESSAGE);
    private final Path decryptedMessagePath = Path.of(FILE_NAME_DECRYPTED_MESSAGE);

    // Write the public key to a file
    public void writePublicKey(BigInteger n, BigInteger d) {
        writeKey(n, d, publicKeyPath);
    }

    // Write the private key to a file
    public void writePrivateKey(BigInteger n, BigInteger e) {
        writeKey(n, e, privateKeyPath);
    }

    private void writeKey(BigInteger n, BigInteger k, Path path) {
        try {
            Files.writeString(path, formatKeyPair(n, k));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Write the encrypted message to a file
    public void writeEncryptedMessage(BigInteger[] encrypted) {
        try {
            String content = Stream.of(encrypted)
                    .map(BigInteger::toString)
                    .collect(Collectors.joining(","));
            Files.write(encryptedMessagePath, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Write the decrypted message to a file
    public void writeDecryptedMessage(BigInteger[] decrypted) {
        try {
            String content = Stream.of(decrypted)
                    .map(bigInteger -> String.valueOf((char) bigInteger.intValue()))
                    .collect(Collectors.joining());
            Files.write(decryptedMessagePath, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Read the public key from a file
    public BigInteger[] readPublicKey() {
        return readKey(FILE_NAME_PUBLIC_KEY);
    }

    // Read the private key from a file
    public BigInteger[] readPrivateKey() {
        return readKey(FILE_NAME_PRIVATE_KEY);
    }

    // Read the public or private key from a file
    private BigInteger[] readKey(String fileName) {
        byte[] content = readTextFile(fileName);
        String[] key = new String(content).substring(1, content.length - 1).split(",");
        return new BigInteger[] { new BigInteger(key[0]), new BigInteger(key[1]) };
    }

    // Read the encrypted message from a file
    public BigInteger[] readCipher() {
        var content = readTextFile(FILE_NAME_ENCRYPTED_MESSAGE);
        var cipher = new String(content).split(",");
        return Stream.of(cipher)
                .map(BigInteger::new)
                .toArray(BigInteger[]::new);
    }

    // Read the text file and return the content as a byte array (ASCII values)
    public byte[] readTextFile(String fileName) {
        try {
            return Files.readAllBytes(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Format the public or private key as a string
    private String formatKeyPair(BigInteger n, BigInteger k) {
        return "(" + n + "," + k + ")";
    }
}
