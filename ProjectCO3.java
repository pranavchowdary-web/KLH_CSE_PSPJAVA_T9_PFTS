import java.util.Scanner;

public class ProjectCO3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] exerciseNames = new String[100];
        int[] durations = new int[100];
        double[] calories = new double[100];

        int workoutCount = 0;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== PERSONAL FITNESS TRACKER ===");
            System.out.println("1. Add a Workout Log");
            System.out.println("2. Display Workout History");
            System.out.println("3. Show Total Calories & Duration");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    if (workoutCount < 100) {
                        System.out.print("Enter Exercise Name (e.g., Running): ");
                        exerciseNames[workoutCount] = sc.nextLine();

                        System.out.print("Enter Duration (in minutes): ");
                        durations[workoutCount] = sc.nextInt();

                        System.out.print("Enter Calories Burned: ");
                        calories[workoutCount] = sc.nextDouble();
                        sc.nextLine();

                        workoutCount++;
                        System.out.println("Workout recorded successfully!");
                    } else {
                        System.out.println("Tracker memory full! Cannot add more entries.");
                    }
                    break;

                case 2:
                    if (workoutCount == 0) {
                        System.out.println("No workout logs found.");
                    } else {
                        System.out.println("\n--- WORKOUT HISTORY ---");
                        for (int i = 0; i < workoutCount; i++) {
                            System.out.println((i + 1) + ". Exercise: " + exerciseNames[i] 
                                + " | Duration: " + durations[i] + " mins" 
                                + " | Calories: " + calories[i] + " kcal");
                        }
                    }
                    break;

                case 3:
                    if (workoutCount == 0) {
                        System.out.println("No workout data available for summary.");
                    } else {
                        int totalDuration = 0;
                        double totalCalories = 0;

                        for (int i = 0; i < workoutCount; i++) {
                            totalDuration += durations[i];
                            totalCalories += calories[i];
                        }

                        System.out.println("\n--- SUMMARY METRICS ---");
                        System.out.println("Total Workouts Logged: " + workoutCount);
                        System.out.println("Total Time Spent: " + totalDuration + " minutes");
                        System.out.println("Total Calories Burned: " + totalCalories + " kcal");
                    }
                    break;

                case 4:
                    isRunning = false;
                    System.out.println("Exiting system. Stay healthy!");
                    break;

                default:
                    System.out.println("Invalid selection! Please enter a number between 1 and 4.");
            }
        }

        sc.close();
    }
}
