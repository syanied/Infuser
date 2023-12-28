package Infuser.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utility class for handling Data file.
 *
 * @author Syanide.
 */

public class DataFileUtil {

    public static final String DATA_FILE_NAME = "data.ins";

    public static void createDataFile() throws IOException {

        try (PrintWriter writer = new PrintWriter(DATA_FILE_NAME)) {

            if (!new File(DATA_FILE_NAME).exists()) {
                new File(DATA_FILE_NAME);
            }

            writer.println("[Do not modify this File if you have no idea what you are doing.]");
            writer.println("-13224394");
            writer.println("-13224394");
            writer.println("-1");
            writer.println("-4843239");
            writer.println("-7237488");
            writer.println("-14014167");
            writer.println("-5313892");
            writer.println("-14606304");
            writer.println("-1");

        }
    }

    public DataFileUtil() {
    }

    public static void writeToDataFile(String data, int line) throws IOException {

        final Path path = Paths.get(DATA_FILE_NAME);

        List<String> lines = Files.readAllLines(path);
        lines.set(line, data);

        Files.write(path, lines);

    }
}
