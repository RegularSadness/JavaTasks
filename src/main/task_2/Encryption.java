import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/*
-d 1a src/main/resources/output/task2_text_encryptionResult.txt -o src/main/resources/output/task2_text_decryptionResult.txt  (дешифровка)
-c 1a src/main/resources/input/task2_text.txt -o src/main/resources/output/task2_text_encryptionResult.txt                    (шифрование)
 */

public class Encryption {

    public static final String ENCRYPT_KEY = "-c";
    public static final String DECRYPT_KEY = "-d";
    public static final String PATH_KEY = "-o";

    public static byte[] encrypt(String inputText, String key) {
        byte[] byteText = inputText.getBytes();
        byte[] byteKey = key.getBytes();
        byte[] byteResult = new byte[byteText.length];

        for (int i = 0; i < byteText.length; i++) {
            byteResult[i] = (byte) (byteText[i] ^ byteKey[i % byteKey.length]);
        }
        return byteResult;
    }

    public static String decrypt(byte[] inputText, String key) {
        byte[] byteResult = new byte[inputText.length];
        byte[] byteKey = key.getBytes();


        for (int i = 0; i < inputText.length; i++) {
            byteResult[i] = (byte) (inputText[i] ^ byteKey[i % byteKey.length]);
        }

        String result = new String(byteResult, StandardCharsets.UTF_8);
        return result;
    }

    public static int getArgKeyIndex(String argKey, int valueCount, List<String> argsList) {
        int keyIndex = argsList.indexOf(argKey);
        if (argsList.size() <= keyIndex + valueCount) {
            throw new IllegalArgumentException(String.format("Please add value for %s", argKey));
        }
        return keyIndex;
    }

    public static void main(String[] args) throws IOException {
        List<String> argsList = Arrays.asList(args);
        if (!((argsList.size() == 3) || (argsList.size() == 5))) {
            throw new IllegalArgumentException("Args is not correct.");
        }

        if (argsList.contains(ENCRYPT_KEY)) {
            int encryptKeyIndex = getArgKeyIndex(ENCRYPT_KEY, 2, argsList);
            String inputFilePath = argsList.get(encryptKeyIndex + 2);
            String encryptionKey = argsList.get(encryptKeyIndex + 1);
            String outputFilePath = inputFilePath;
            if (argsList.contains(PATH_KEY)) {
                int pathKeyIndex = getArgKeyIndex(PATH_KEY, 1, argsList);
                outputFilePath = argsList.get(pathKeyIndex + 1);
            }

            String content = Files.readString(Paths.get(inputFilePath));
            Files.write(Paths.get(outputFilePath), encrypt(content, encryptionKey));

        } else if (argsList.contains(DECRYPT_KEY)) {
            int decryptKeyIndex = getArgKeyIndex(DECRYPT_KEY, 2, argsList);
            String inputFilePath = argsList.get(decryptKeyIndex + 2);
            String decryptionKey = argsList.get(decryptKeyIndex + 1);
            String outputFilePath = inputFilePath;
            if (argsList.contains(PATH_KEY)) {
                int pathKeyIndex = getArgKeyIndex(PATH_KEY, 1, argsList);
                outputFilePath = argsList.get(pathKeyIndex + 1);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write(decrypt(Files.readAllBytes(Paths.get(inputFilePath)), decryptionKey));
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException("Writing to file fail");
            }

        } else {
            throw new IllegalArgumentException(String.format("args should contains %s or %s key", ENCRYPT_KEY, DECRYPT_KEY));
        }
    }
}
