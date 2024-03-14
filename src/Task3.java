import java.math.BigInteger;

public class Task3 {
    public static void main(String[] args) {
        IOUtility ioUtility = new IOUtility();
        RSAUtility rsaUtility = new RSAUtility();

        BigInteger[] pKey = ioUtility.readPrivateKey();
        BigInteger[] cipher = ioUtility.readCipher();
        BigInteger[] decrypted = new BigInteger[cipher.length];
        for (int i = 0; i < cipher.length; i++) {
            System.out.println("Cipher[" + i + "]: " + rsaUtility.decrypt(pKey[0], pKey[1], cipher[i]));
            decrypted[i] = rsaUtility.decrypt(pKey[0], pKey[1], cipher[i]);
        }
        ioUtility.writeDecryptedMessage(decrypted);
    }
}
