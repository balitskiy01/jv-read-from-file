package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileWork {
    public String[] readFromFile(String fileName) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[\\s\\p{Punct}]+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        String cleanedWord = word.replaceAll("[\\p{Punct}]", "").toLowerCase();
                        if (cleanedWord.startsWith("w")) {
                            result.add(cleanedWord);
                        }
                    }
                }
            }
            Collections.sort(result);
            return result.isEmpty() ? new String[0] : result.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
    }
}
