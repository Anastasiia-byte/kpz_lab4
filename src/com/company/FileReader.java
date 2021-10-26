package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileReader {
    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), StandardCharsets.UTF_8);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return sb.toString();
    }
}
