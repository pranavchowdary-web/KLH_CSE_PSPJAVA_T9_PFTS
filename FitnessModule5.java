import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FitnessModule5 {
    public static void main(String[] args) {
        String logText = "   Cardio-Running-Session   ";
        String trimmed = logText.trim();
        System.out.println("Length: " + trimmed.length() + ", Char at 6: " + trimmed.charAt(6));
        System.out.println("Substring: " + trimmed.substring(6, 13) + ", Upper: " + trimmed.toUpperCase());

        String s1 = "Running";
        String s2 = new String("Running");
        System.out.println("Reference check (==): " + (s1 == s2));
        System.out.println("Value check (equals): " + s1.equals(s2));

        StringBuilder sBuilder = new StringBuilder("Workout");
        sBuilder.append(" Log").insert(7, "-Daily");
        StringBuffer sBuffer = new StringBuffer("Thread-Safe Nutrition Log");
        System.out.println("StringBuilder: " + sBuilder + " | StringBuffer: " + sBuffer);

        String rawRecord = "W-201, Running, 30 mins, 350 kcal";
        String[] tokens = rawRecord.split(",");
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
        }
        String joinedText = String.join(" | ", tokens);
        System.out.println("Parsed & Joined: " + joinedText);

        List<String> activities = new ArrayList<>(Arrays.asList("Yoga", "Cycling", "Running"));
        Collections.sort(activities);
        Collections.sort(activities, Comparator.reverseOrder());
        System.out.println("Sorted Activities: " + activities);

        double[] calories = {250.5, 120.0, 500.3, 330.1};
        Arrays.sort(calories);
        int foundIndex = Arrays.binarySearch(calories, 500.3);
        System.out.println("Binary search index of 500.3: " + foundIndex);

        Path path = Paths.get("fitness_logs.txt");
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write("Log 1: Morning Running - 300 kcal\n");
            }
            try (BufferedWriter appender = Files.newBufferedWriter(path, java.nio.file.StandardOpenOption.APPEND)) {
                appender.write("Log 2: Evening Yoga - 150 kcal\n");
            }

            System.out.println("--- Reading Fitness File Line-by-Line ---");
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    System.out.println("Line: " + currentLine);
                }
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}