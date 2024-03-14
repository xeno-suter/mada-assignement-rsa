import java.math.BigInteger;

public class Task2 {

    public static String TEXT_FILE = "text.txt";

    public static void main(String[] args) {
        IOUtility ioUtility = new IOUtility();
        BigIntegerUtility bigIntegerUtility = new BigIntegerUtility();
        byte[] text = ioUtility.readTextFile(TEXT_FILE);

        for (int i = 0; i < text.length; i++) {
            System.out.println("Text[" + i + "]: " + text[i]);
        }
        System.out.println("Text: " + new String(text));

        // fast exponentiation
        var x = bigIntegerUtility.fastExponentiation(
                new BigInteger("7"),
                new BigInteger("13"),
                new BigInteger("11")
        );
        System.out.println("x: " + x);
    }
}
