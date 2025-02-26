package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Enablers {

    /*** Writes the given content to a file with the specified name.
     * @param fileName the name of the file to write to
     * @param fileContent the content to write to the file */
    public static void WriteToFile(String fileName, String fileContent) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            // Write the content to the file
            fileWriter.write(fileContent);
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}