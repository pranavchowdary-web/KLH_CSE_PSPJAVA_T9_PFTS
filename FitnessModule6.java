import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Workout {
    String name;
    String type;
    int calories;

    public Workout(String name, String type, int calories) {
        this.name = name;
        this.type = type;
        this.calories = calories;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getCalories() { return calories; }

    @Override
    public String toString() {
        return name + " (" + type + ") - " + calories + " kcal";
    }
}

public class FitnessModule6 {
    public static void main(String[] args) {
        List<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout("Morning Run", "Cardio", 350));
        workouts.add(new Workout("Pushups", "Strength", 200));
        workouts.add(new Workout("Evening Jog", "Cardio", 400));

        Set<String> uniqueTypes = new HashSet<>();
        uniqueTypes.add("Cardio");
        uniqueTypes.add("Strength");

        Map<String, Workout> workoutMap = new HashMap<>();
        workoutMap.put("W1", workouts.get(0));

        Map<String, String> orderedMap = new TreeMap<>();
        orderedMap.put("Z", "Yoga");
        orderedMap.put("A", "Cardio");

        for (Workout w : workouts) {
            System.out.println(w);
        }

        Iterator<Workout> it = workouts.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        Predicate<Workout> isCardio = w -> w.getType().equals("Cardio");
        Consumer<Workout> printer = System.out::println;
        Function<Workout, String> getName = Workout::getName;
        Supplier<Workout> defaultWorkout = () -> new Workout("Rest", "None", 0);
        Comparator<Workout> comp = Comparator.comparingInt(Workout::getCalories);

        workouts.stream().filter(isCardio).forEach(printer);

        List<String> topCardio = workouts.stream()
                .filter(isCardio)
                .sorted(comp.reversed())
                .map(getName)
                .distinct()
                .limit(2)
                .collect(Collectors.toList());

        long count = workouts.stream().count();

        int totalCalories = workouts.stream()
                .mapToInt(Workout::getCalories)
                .reduce(0, (a, b) -> a + b);

        Map<String, Long> typeCount = workouts.stream()
                .collect(Collectors.groupingBy(Workout::getType, Collectors.counting()));

        String joinedNames = workouts.stream()
                .map(Workout::getName)
                .collect(Collectors.joining(", "));

        Path path = Paths.get("fitness_logs.txt");
        if (Files.exists(path)) {
            try (Stream<String> lineStream = Files.lines(path)) {
                lineStream.filter(l -> l.contains("Run")).forEach(System.out::println);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}