class InvalidActivityException extends Exception {
    public InvalidActivityException(String message) {
        super(message);
    }
}

class InvalidDurationException extends RuntimeException {
    public InvalidDurationException(String message) {
        super(message);
    }
}

interface Trackable {
    double calculateCalories();
    String getSummary();
}

abstract class FitnessActivity implements Trackable {
    private String activityName;
    private int durationMinutes;
    private double intensityFactor;

    public static int totalActivitiesTracked = 0;
    public static final String TRACKER_VERSION = "v1.0.0";

    public FitnessActivity() {
        this("Default Activity", 10, 1.0);
    }

    public FitnessActivity(String activityName, int durationMinutes, double intensityFactor) {
        this.activityName = activityName;
        setDurationMinutes(durationMinutes);
        this.intensityFactor = intensityFactor;
        totalActivitiesTracked++;
    }

    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes <= 0) {
            throw new InvalidDurationException("Duration must be greater than zero!");
        }
        this.durationMinutes = durationMinutes;
    }

    public double getIntensityFactor() { return intensityFactor; }

    public static void displaySystemInfo() {
        System.out.println("System Version: " + TRACKER_VERSION);
    }

    @Override
    public String toString() {
        return "Activity: " + activityName + " | Duration: " + durationMinutes + " mins";
    }
}

class CardioActivity extends FitnessActivity {
    private double distanceKm;

    public CardioActivity(String activityName, int durationMinutes, double intensityFactor, double distanceKm) {
        super(activityName, durationMinutes, intensityFactor);
        this.distanceKm = distanceKm;
    }

    @Override
    public double calculateCalories() {
        return getDurationMinutes() * getIntensityFactor() * (distanceKm > 0 ? distanceKm * 1.2 : 1.0);
    }

    @Override
    public String getSummary() {
        return String.format("[Cardio] %s - %d mins -> %.2f kcal", getActivityName(), getDurationMinutes(), calculateCalories());
    }
}

public class Module4_OOP_Exceptions {
    public static void validateActivity(String name) throws InvalidActivityException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidActivityException("Activity name cannot be null or empty!");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MODULE 4: OOP & EXCEPTION HANDLING ===");
        
        FitnessActivity.displaySystemInfo();

        FitnessActivity activityRef = null;

        try {
            String inputName = "Running";
            validateActivity(inputName);

            activityRef = new CardioActivity(inputName, 30, 2.5, 5.0);
            
            System.out.println(activityRef.toString());
            System.out.println(activityRef.getSummary());

            activityRef.setDurationMinutes(-5);

        } catch (InvalidActivityException | InvalidDurationException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        } finally {
            System.out.println("Execution of try-catch-finally block completed.");
        }

        if (activityRef != null) {
            System.out.println("Total Activities Tracked (Static): " + FitnessActivity.totalActivitiesTracked);
        }
    }
}