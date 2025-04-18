package org.keyin;

import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassService;

import java.sql.SQLException;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Register new user");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerUser(scanner, userService);
                case 2 -> logInAsUser(scanner, userService, membershipService, workoutService);
                case 3 -> System.out.println("Exiting the program...");
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void registerUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        userService.registerUser(username, password, role, email, phone, address);
    }

    private static void logInAsUser(Scanner scanner, UserService userService, MembershipService membershipService, WorkoutClassService workoutService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.authenticateUser(username, password);
            if (user != null) {
                System.out.println("Login Successful! Welcome " + user.getUserName());
                switch (user.getUserRole().toLowerCase()) {
                    case "admin" -> showAdminMenu(scanner, user, userService, membershipService);
                    case "trainer" -> showTrainerMenu(scanner, user, workoutService, membershipService);
                    case "member" -> showMemberMenu(scanner, user, membershipService, workoutService);
                    default -> System.out.println("Unknown role.");
                }
            } else {
                System.out.println("Login Failed! Invalid credentials.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

    private static void showAdminMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService) {
        boolean back = false;
        while (!back) {
            System.out.println("Admin Menu:");
            System.out.println("1. View all users");
            System.out.println("2. Delete user by username");
            System.out.println("3. View membership revenue");
            System.out.println("0. Logout");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> userService.viewAllUsers();
                case "2" -> {
                    System.out.print("Enter username to delete: ");
                    String targetUser = scanner.nextLine();
                    userService.deleteUserByUsername(targetUser);
                }
                case "3" -> membershipService.calculateTotalRevenue();
                case "0" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void showTrainerMenu(Scanner scanner, User user, WorkoutClassService workoutService, MembershipService membershipService) {
        boolean back = false;
        while (!back) {
            System.out.println("Trainer Menu:");
            System.out.println("1. Create workout class");
            System.out.println("2. Update workout class");
            System.out.println("3. Delete workout class");
            System.out.println("4. View my workout classes");
            System.out.println("5. Add membership for a user");
            System.out.println("0. Logout");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.print("Enter class type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    workoutService.createWorkoutClass(type, desc, user.getUserId());
                }
                case "2" -> {
                    System.out.print("Enter class ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String desc = scanner.nextLine();
                    workoutService.updateWorkoutClass(id, type, desc, user.getUserId());
                }
                case "3" -> {
                    System.out.print("Enter class ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    workoutService.deleteWorkoutClass(id);
                }
                case "4" -> workoutService.viewClassesByTrainer(user.getUserId());
                case "5" -> {
                    System.out.print("Enter membership type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter price: ");
                    int price = Integer.parseInt(scanner.nextLine());
                    membershipService.addMembership(type, price, desc, user.getUserId());
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void showMemberMenu(Scanner scanner, User user, MembershipService membershipService, WorkoutClassService workoutService) {
        boolean back = false;
        while (!back) {
            System.out.println("Member Menu:");
            System.out.println("1. View all workout classes");
            System.out.println("2. Purchase membership");
            System.out.println("3. View total spent");
            System.out.println("0. Logout");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> workoutService.viewAllWorkoutClasses();
                case "2" -> {
                    System.out.print("Enter membership type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter price: ");
                    int price = Integer.parseInt(scanner.nextLine());
                    membershipService.addMembership(type, price, desc, user.getUserId());
                }
                case "3" -> membershipService.viewTotalExpensesForUser(user.getUserId());
                case "0" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
