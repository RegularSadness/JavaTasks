import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;

public class EncryptionTest {

    @Test
    public void encryptionTest() {
        byte[] actualResult = Encryption.encryption("Съешь ещё этих мягких французских булок, да выпей же чаю.", "1a");
        byte[] expectedResult = {-31, -64, -32, -21, -31, -44, -32, -23, -32, -19,
                17, -79, -124, -80, -72, -80, -96, 65, -32, -20,
                -32, -29, -31, -39, -32, -28, 17, -79, -115, -80,
                -66, -79, -126, -79, -117, -79, -119, -80, -76, 65,
                -32, -27, -32, -31, -31, -47, -31, -36, -32, -25, -32,
                -30, -31, -42, -32, -32, -31, -37, -31, -39, -32, -28,
                17, -79, -128, -80, -78, -79, -118, -79, -113, -79, -117,
                77, 17, -79, -123, -79, -127, 65, -31, -45, -32, -22, -31,
                -34, -31, -44, -31, -40, 17, -79, -121, -79, -124, 65, -32,
                -26, -31, -47, -32, -17, 31};
        Assert.assertEquals(Arrays.toString(expectedResult), Arrays.toString(actualResult));
    }

    @Test
    public void decryptionTest() {
        String actualResult = Encryption.decryption(Encryption.encryption("Съешь ещё этих мягких французских булок, да выпей же чаю.", "10"), "10");
        String expectedResult = "Съешь ещё этих мягких французских булок, да выпей же чаю.";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void textReaderTest() throws IOException {
        String actualResult = Encryption.textReader("C:/Users/Мишаня/IdeaProjects/JavaTasks/src/main/resources/input/task2_text.txt");
        String expectedResult = "Съешь ещё этих мягких французских булок, да выпей же чаю.";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void byteReaderTest() throws IOException {
        byte[] actualResult = Encryption.byteReader("C:/Users/Мишаня/IdeaProjects/JavaTasks/src/main/resources/output/task2_text_encryptionResult.txt");
        byte[] expectedResult = {-31, -64, -32, -21, -31, -44, -32, -23, -32, -19,
                17, -79, -124, -80, -72, -80, -96, 65, -32, -20,
                -32, -29, -31, -39, -32, -28, 17, -79, -115, -80,
                -66, -79, -126, -79, -117, -79, -119, -80, -76, 65,
                -32, -27, -32, -31, -31, -47, -31, -36, -32, -25, -32,
                -30, -31, -42, -32, -32, -31, -37, -31, -39, -32, -28,
                17, -79, -128, -80, -78, -79, -118, -79, -113, -79, -117,
                77, 17, -79, -123, -79, -127, 65, -31, -45, -32, -22, -31,
                -34, -31, -44, -31, -40, 17, -79, -121, -79, -124, 65, -32,
                -26, -31, -47, -32, -17, 31};
        Assert.assertEquals(Arrays.toString(expectedResult), Arrays.toString(actualResult));
    }

}
