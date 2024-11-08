package org.car.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegistrationFinder {
    private static final String PATTERN = "\\b[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}\\b";
    public static List<String> registrationList(String path) {
        List<String> registrations = new ArrayList<>();

        Pattern pattern = Pattern.compile(PATTERN);
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    registrations.add(matcher.group());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return registrations;
    }
}
