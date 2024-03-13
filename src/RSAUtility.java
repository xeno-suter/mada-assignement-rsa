import java.math.BigInteger;

public class RSAUtility {
    public boolean canBeEncrypted(BigInteger n, BigInteger message) {
        // Check if the message is within the range of the public key n -> 0 < message < n
        return message.compareTo(n) < 0 && message.compareTo(BigInteger.ZERO) > 0;
    }

    public BigInteger encrypt(BigInteger n, BigInteger e, BigInteger message) {
        // Encrypt message using the public key (n, e) -> c = m^e mod n
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger n, BigInteger d, BigInteger cipher) {
        // Decrypt message using the private key (n, d) and the cipher text -> m = c^d mod n
        return cipher.modPow(d, n);
    }
}
