package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    /**
     * Writes the given content to a file with the specified name.
     *
     * @param fileName    the name of the file to write to
     * @param fileContent the content to write to the file
     */
    public static void WriteToFile(String fileName, String fileContent) {
        // Create a new file object with the specified file name
        File file = new File(fileName);
        // Use a try-with-resources statement to ensure the FileWriter is closed properly
        try (FileWriter fileWriter = new FileWriter(file)) {
            // Write the content to the file
            fileWriter.write(fileContent);
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}