import java.util.Scanner;

public class ProjectCO2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many workouts do you want to log today? ");
        int numberOfWorkouts = sc.nextInt();

        int totalDuration = 0;
        double totalCalories = 0;
        for (int i = 1; i <= numberOfWorkouts; i++) {
            System.out.println("\nWorkout #" + i);

            System.out.print("Enter duration (mins): ");
            int minutes = sc.nextInt();

            System.out.print("Enter calories burned: ");
            double calories = sc.nextDouble();

            totalDuration = totalDuration + minutes;
            totalCalories = totalCalories + calories;
        }

        System.out.println("\n--- FINAL SUMMARY ---");
        System.out.println("Total Workouts: " + numberOfWorkouts);
        System.out.println("Total Time: " + totalDuration + " minutes");
        System.out.println("Total Calories: " + totalCalories + " kcal");

        if (totalDuration >= 30) {
            System.out.println("Goal Met: Great job staying active!");
        } else {
            System.out.println("Goal Pending: Try to reach 30 minutes of activity tomorrow.");
        }
            
        sc.close();
    }
}