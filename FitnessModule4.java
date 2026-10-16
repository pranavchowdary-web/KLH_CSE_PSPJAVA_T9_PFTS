interface Trackable {
    void trackActivity() throws InvalidWorkoutException;
}

class InvalidWorkoutException extends Exception {
    public InvalidWorkoutException(String message) {
        super(message);
    }
}

public class FitnessModule4 implements Trackable {
    private String workoutId;
    private String activityName;

    public static final String PLATFORM_NAME = "Personal Fitness System";
    private static int totalWorkoutsLogged = 0;

    public FitnessModule4() {
        this("W-DEFAULT", "General");
    }

    public FitnessModule4(String workoutId, String activityName) {
        this.workoutId = workoutId;
        this.activityName = activityName;
        totalWorkoutsLogged++;
    }

    public String getWorkoutId() { return workoutId; }
    public void setWorkoutId(String workoutId) { this.workoutId = workoutId; }

    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }

    public static int getTotalWorkoutsLogged() { return totalWorkoutsLogged; }

    @Override
    public void trackActivity() throws InvalidWorkoutException {
        if (workoutId == null || workoutId.equals("W-DEFAULT")) {
            throw new InvalidWorkoutException("Invalid Workout ID!");
        }
    }

    @Override
    public String toString() {
        return "Platform: " + PLATFORM_NAME + ", Workout ID: " + workoutId + ", Activity: " + activityName;
    }

    public static void main(String[] args) {
        FitnessModule4 tracker = null;

        try {
            tracker = new FitnessModule4("W101", "Running");
            tracker.trackActivity();
            System.out.println(tracker);

            FitnessModule4 faultyTracker = new FitnessModule4();
            faultyTracker.trackActivity();

        } catch (InvalidWorkoutException | NullPointerException e) {
            System.err.println("Exception Caught: " + e.getMessage());
        } finally {
            System.out.println("Workout tracking session completed.");
        }
        
        System.out.println("Total Workouts Logged: " + FitnessModule4.getTotalWorkoutsLogged());
    }
}