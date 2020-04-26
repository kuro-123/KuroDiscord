package host.kuro.kurodiscord.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public static String readFile(File file, String encode) throws IOException {
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        return readFile(new FileInputStream(file), encode);
    }

    public static String readFile(String filename, String encode) throws IOException {
        File file = new File(filename);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        return readFile(new FileInputStream(file), encode);
    }

    public static String readFile(InputStream inputStream, String encode) throws IOException {
        return readFile(new InputStreamReader(inputStream, encode));
    }

    private static String readFile(Reader reader) throws IOException {
        try (BufferedReader br = new BufferedReader(reader)) {
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            temp = br.readLine();
            while (temp != null) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(temp);
                temp = br.readLine();
            }
            return stringBuilder.toString();
        }
    }

}
