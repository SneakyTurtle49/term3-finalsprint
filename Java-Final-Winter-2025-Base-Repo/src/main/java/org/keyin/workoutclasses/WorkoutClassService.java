package org.keyin.workoutclasses;

import java.sql.SQLException;
import java.util.List;

public class WorkoutClassService {

    private final WorkoutClassDAO workoutClassDAO = new WorkoutClassDAO();

    // Trainer: Create a new workout class
    public void createWorkoutClass(String type, String description, int trainerId) {
        WorkoutClass workoutClass = new WorkoutClass(type, description, trainerId);
        try {
            workoutClassDAO.addWorkoutClass(workoutClass);
            System.out.println("Workout class successfully created.");
        } catch (SQLException e) {
            System.out.println("Failed to create workout class: " + e.getMessage());
        }
    }

    // Trainer: Update an existing workout class
    public void updateWorkoutClass(int classId, String type, String description, int trainerId) {
        WorkoutClass workoutClass = new WorkoutClass(classId, type, description, trainerId);
        try {
            workoutClassDAO.updateWorkoutClass(workoutClass);
            System.out.println("Workout class successfully updated.");
        } catch (SQLException e) {
            System.out.println("Failed to update workout class: " + e.getMessage());
        }
    }

    // Trainer: Delete a workout class
    public void deleteWorkoutClass(int classId) {
        try {
            workoutClassDAO.deleteWorkoutClass(classId);
            System.out.println("Workout class deleted.");
        } catch (SQLException e) {
            System.out.println("Failed to delete workout class: " + e.getMessage());
        }
    }

    // Trainer: View all classes they are assigned to
    public void viewClassesByTrainer(int trainerId) {
        try {
            List<WorkoutClass> classes = workoutClassDAO.getWorkoutClassesByTrainerId(trainerId);
            if (classes.isEmpty()) {
                System.out.println("ℹNo classes found for this trainer.");
            } else {
                System.out.println("Your Assigned Classes:");
                for (WorkoutClass wc : classes) {
                    System.out.printf("- [%d] %s: %s%n", wc.getWorkoutClassId(), wc.getWorkoutClassType(), wc.getWorkoutClassDescription());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trainer's classes: " + e.getMessage());
        }
    }

    // Member: View all available classes
    public void viewAllWorkoutClasses() {
        try {
            List<WorkoutClass> classes = workoutClassDAO.getAllWorkoutClasses();
            if (classes.isEmpty()) {
                System.out.println("No workout classes available.");
            } else {
                System.out.println("All Available Workout Classes:");
                for (WorkoutClass wc : classes) {
                    System.out.printf("- [%d] %s (Trainer ID: %d): %s%n", wc.getWorkoutClassId(), wc.getWorkoutClassType(), wc.getTrainerId(), wc.getWorkoutClassDescription());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving classes: " + e.getMessage());
        }
    }
}