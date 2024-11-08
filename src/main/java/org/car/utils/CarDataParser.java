package org.car.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CarDataParser {

    public static Map<String, String[]> parseCarData(String path) throws IOException {
        Map<String, String[]> carData = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                carData.put(values[0], values);
            }
        }
        return carData;
    }
}
