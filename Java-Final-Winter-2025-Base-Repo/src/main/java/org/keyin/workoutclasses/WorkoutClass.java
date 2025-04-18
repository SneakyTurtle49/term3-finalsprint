package org.keyin.workoutclasses;

public class WorkoutClass {
    private int workoutClassId;
    private String workoutClassType;
    private String workoutClassDescription;
    private int trainerId; // references a user with role 'Trainer'

    // Constructors
    public WorkoutClass(int workoutClassId, String workoutClassType, String workoutClassDescription, int trainerId) {
        this.workoutClassId = workoutClassId;
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
    }

    public WorkoutClass(String workoutClassType, String workoutClassDescription, int trainerId) {
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
    }

    // Getters and Setters
    public int getWorkoutClassId() {
        return workoutClassId;
    }

    public void setWorkoutClassId(int workoutClassId) {
        this.workoutClassId = workoutClassId;
    }

    public String getWorkoutClassType() {
        return workoutClassType;
    }

    public void setWorkoutClassType(String workoutClassType) {
        this.workoutClassType = workoutClassType;
    }

    public String getWorkoutClassDescription() {
        return workoutClassDescription;
    }

    public void setWorkoutClassDescription(String workoutClassDescription) {
        this.workoutClassDescription = workoutClassDescription;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
