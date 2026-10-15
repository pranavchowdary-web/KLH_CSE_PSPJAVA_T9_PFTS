import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

class WorkoutLog implements Comparable<WorkoutLog> {
    private String name;
    private int duration;

    public WorkoutLog(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() { return name; }
    public int getDuration() { return duration; }

    @Override
    public int compareTo(WorkoutLog other) {
        return Integer.min(this.duration, other.duration); // Natural Ordering
    }

    @Override
    public String toString() { return name + " (" + duration + ")"; }
}

class LogNameComparator implements Comparator<WorkoutLog> {
    @Override
    public int compare(WorkoutLog w1, WorkoutLog w2) {
        return w1.getName().compareToIgnoreCase(w2.getName()); // Custom Ordering
    }
}

public class Module5_Strings_Algorithms_FileIO {
    private static final String FILE_NAME = "workout_records.txt";

    public static void main(String[] args) {
        // 1. Strings & Mutable Strings
        String raw = "  Cycling,45,Cardio  ";
        String trimmed = raw.trim();
        String sub = raw.substring(2, 9);
        boolean hasCardio = trimmed.contains("Cardio");

        // String Comparison: equals() vs ==
        String s1 = "Workout";
        String s2 = new String("Workout");
        System.out.println(s1.equals(s2) + " | " + (s1 == s2));

        // Mutable Strings (StringBuilder & StringBuffer)
        StringBuilder sb = new StringBuilder("Log: ").append(trimmed);
        StringBuffer sbf = new StringBuffer("SafeLog");

        // Tokenization with split() and join()
        String[] tokens = trimmed.split(",");
        String rejoined = String.join(" - ", tokens);

        // 2. Library Algorithms & Sorting (Comparable & Comparator)
        int[] scores = {45, 10, 90, 30};
        Arrays.sort(scores);
        int idx = Arrays.binarySearch(scores, 30);

        List<WorkoutLog> logs = new ArrayList<>();
        logs.add(new WorkoutLog("Running", 30));
        logs.add(new WorkoutLog("Yoga", 60));

        Collections.sort(logs); // Sorting via Comparable
        logs.sort(new LogNameComparator()); // Sorting via Comparator

        // 3. File I/O (NIO.2) with Try-with-Resources & Error Handling
        Path path = Paths.get(FILE_NAME);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("Running,30");
            writer.newLine();
            writer.write("MalformedLineWithoutComma");
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        try {
                            Integer.parseInt(parts[1].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Skipped invalid number");
                        }
                    } else {
                        System.out.println("Skipped malformed structure");
                    }
                }
            } catch (IOException e) {
                System.out.println("Read error: " + e.getMessage());
            }
        }
    }
}