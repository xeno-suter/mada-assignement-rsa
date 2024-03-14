import java.math.BigInteger;

public class Task4 {
    // works like task 3
    // however use the cipher.txt and the sk.txt file you provided
    // copy them into the root of the project
    public static void main(String[] args) {
        IOUtility ioUtility = new IOUtility();
        RSAUtility rsaUtility = new RSAUtility();

        BigInteger[] pKey = ioUtility.readPrivateKey();
        BigInteger[] cipher = ioUtility.readCipher();
        BigInteger[] decrypted = new BigInteger[cipher.length];
        for (int i = 0; i < cipher.length; i++) {
            decrypted[i] = rsaUtility.decrypt(pKey[0], pKey[1], cipher[i]);
        }
        ioUtility.writeDecryptedMessage(decrypted);
    }
}
