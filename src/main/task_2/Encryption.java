import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
-d 1a src/main/resources/output/task2_text_encryptionResult.txt -o src/main/resources/output/task2_text_decryptionResult.txt  (дешифровка)
-c 1a src/main/resources/input/task2_text.txt -o src/main/resources/output/task2_text_encryptionResult.txt                    (шифрование)
 */

public class Encryption {

    public static byte[] encryption(String inputText, String key) {
        byte[] byteText = inputText.getBytes();
        byte[] byteKey = key.getBytes();
        byte[] byteResult = new byte[byteText.length];

        for (int i = 0; i < byteText.length; i++) {
            byteResult[i] = (byte) (byteText[i] ^ byteKey[i % byteKey.length]);
        }
        return byteResult;
    }

    public static String decryption(byte[] inputText, String key) {
        byte[] byteResult = new byte[inputText.length];
        byte[] byteKey = key.getBytes();


        for (int i = 0; i < inputText.length; i++) {
            byteResult[i] = (byte) (inputText[i] ^ byteKey[i % byteKey.length]);
        }

        String result = new String(byteResult, StandardCharsets.UTF_8);
        return result;
    }

    public static void bufferedWriter(String path, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public static byte[] byteReader(String path) throws IOException {
        byte[] content = Files.readAllBytes(Paths.get(path));
        return content;
    }

    public static String textReader(String path) throws IOException {
        String content = Files.readString(Paths.get(path));
        return content;
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("-c")) {
            if (args[3].equals("-o")) {
                Files.write(Paths.get(args[4]), encryption(textReader(args[2]), args[1]));
            } else {
                Files.write(Paths.get(args[2]), encryption(textReader(args[2]), args[1]));
            }

        } else if (args.length > 0 && args[0].equals("-d")) {
            if (args[3].equals("-o")) {
                bufferedWriter(args[4], decryption(byteReader(args[2]), args[1]));
            } else {
                bufferedWriter(args[2], decryption(byteReader(args[2]), args[1]));
            }
        } else {
            throw new IllegalArgumentException("Invalid key");
        }
    }
}
