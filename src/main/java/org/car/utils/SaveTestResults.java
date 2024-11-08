package org.car.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class SaveTestResults {

    private BufferedWriter writer;

    public SaveTestResults(String path) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(path, true));
        } catch (IOException e) {
            System.out.println("Failed to open file");
        }
    }
    public void logRegistration(String regNumber) {
        try {
            writer.write(regNumber);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }
    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Failed to close file writer");
        }
    }
}
