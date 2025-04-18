package org.keyin.workoutclasses;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAO {

    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO workout_classes (workout_class_type, workout_class_description, trainer_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setString(2, workoutClass.getWorkoutClassDescription());
            pstmt.setInt(3, workoutClass.getTrainerId());

            pstmt.executeUpdate();
        }
    }

    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "UPDATE workout_classes SET workout_class_type = ?, workout_class_description = ?, trainer_id = ? WHERE workout_class_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setString(2, workoutClass.getWorkoutClassDescription());
            pstmt.setInt(3, workoutClass.getTrainerId());
            pstmt.setInt(4, workoutClass.getWorkoutClassId());

            pstmt.executeUpdate();
        }
    }

    public void deleteWorkoutClass(int workoutClassId) throws SQLException {
        String sql = "DELETE FROM workout_classes WHERE workout_class_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, workoutClassId);
            pstmt.executeUpdate();
        }
    }

    public List<WorkoutClass> getWorkoutClassesByTrainerId(int trainerId) throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    WorkoutClass workoutClass = new WorkoutClass(
                            rs.getInt("workout_class_id"),
                            rs.getString("workout_class_type"),
                            rs.getString("workout_class_description"),
                            rs.getInt("trainer_id")
                    );
                    classes.add(workoutClass);
                }
            }
        }

        return classes;
    }

    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("workout_class_id"),
                        rs.getString("workout_class_type"),
                        rs.getString("workout_class_description"),
                        rs.getInt("trainer_id")
                );
                classes.add(workoutClass);
            }
        }

        return classes;
    }
}