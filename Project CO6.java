import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Exercise {
    private String name;
    private int duration;
    private String category;

    public Exercise(String name, int duration, String category) {
        this.name = name;
        this.duration = duration;
        this.category = category;
    }

    public String getName() { return name; }
    public int getDuration() { return duration; }
    public String getCategory() { return category; }
}

public class Module6_Collections_Streams {
    public static void main(String[] args) {
        // 1. Collections (List, Set, Map), Generics & Autoboxing
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Running", 45, "Cardio"));
        exercises.add(new Exercise("Pushups", 20, "Strength"));
        exercises.add(new Exercise("Running", 45, "Cardio"));

        Set<String> uniqueCategories = new HashSet<>();
        Map<String, Integer> counts = new HashMap<>();

        for (Exercise ex : exercises) {
            uniqueCategories.add(ex.getCategory());
            counts.put(ex.getName(), counts.getOrDefault(ex.getName(), 0) + 1); // Autoboxing
        }

        // Iterator
        Iterator<Exercise> it = exercises.iterator();
        while (it.hasNext()) { it.next(); }

        // 2. Functional Interfaces, Lambdas, Method References & Comparator
        Predicate<Exercise> isLong = e -> e.getDuration() > 30;
        Function<Exercise, String> getNameFunc = Exercise::getName;
        Consumer<String> printConsumer = System.out::println;
        Supplier<String> defaultSupplier = () -> "Default";
        Comparator<Exercise> comp = Comparator.comparing(Exercise::getDuration);

        // 3. Stream Pipelines (Intermediate & Terminal Operations)
        List<String> resultList = exercises.stream()
                .filter(isLong)
                .map(getNameFunc)
                .distinct()
                .sorted()
                .limit(2)
                .collect(Collectors.toList());

        long totalCount = exercises.stream().count();
        int totalDuration = exercises.stream()
                .map(Exercise::getDuration)
                .reduce(0, Integer::sum);

        // 4. Advanced Collectors
        Map<String, Long> grouped = exercises.stream()
                .collect(Collectors.groupingBy(Exercise::getCategory, Collectors.counting()));

        String joined = exercises.stream()
                .map(Exercise::getName)
                .collect(Collectors.joining(", "));

        // 5. Reading File Lines as a Stream (NIO.2)
        Path file = Paths.get("workout_records.txt");
        if (Files.exists(file)) {
            try (Stream<String> lineStream = Files.lines(file)) {
                lineStream.forEach(printConsumer);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}