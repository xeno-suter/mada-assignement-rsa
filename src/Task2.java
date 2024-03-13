public class Task2 {

    public static String TEXT_FILE = "text.txt";

    public static void main(String[] args) {
        IOUtility ioUtility = new IOUtility();
        byte[] text = ioUtility.readTextFile(TEXT_FILE);

        for (int i = 0; i < text.length; i++) {
            System.out.println("Text[" + i + "]: " + text[i]);
        }
        System.out.println("Text: " + new String(text));
    }
}
