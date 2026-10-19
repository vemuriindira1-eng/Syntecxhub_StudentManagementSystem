
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadStudents();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }

    // Add a new student
    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = readInt();

        if (id <= 0) {
            System.out.println("Student ID must be positive.");
            return;
        }

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Student ID already exists!");
                return;
            }
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Student Age: ");
        int age = readInt();

        System.out.print("Enter Course: ");
        String course = sc.nextLine().trim();

        if (age <= 0 || name.isEmpty() || course.isEmpty()
                || name.contains("|") || course.contains("|")) {
            System.out.println("Invalid details. Please try again.");
            return;
        }

        students.add(new Student(id, name, age, course));
        saveStudents();

        System.out.println("Student added successfully!");
    }

    // View all students
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-10s %-20s %-10s %-20s%n",
                "ID", "Name", "Age", "Course");
        System.out.println("------------------------------------------------------------");

        for (Student s : students) {
            s.displayStudent();
        }
    }

    // Search for a student by ID
    static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = readInt();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.printf("%-10s %-20s %-10s %-20s%n",
                        "ID", "Name", "Age", "Course");
                System.out.println("------------------------------------------------------------");
                s.displayStudent();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Update student details
    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = readInt();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                String name = sc.nextLine().trim();

                System.out.print("Enter new age: ");
                int age = readInt();

                System.out.print("Enter new course: ");
                String course = sc.nextLine().trim();

                if (name.isEmpty() || age <= 0 || course.isEmpty()
                        || name.contains("|") || course.contains("|")) {
                    System.out.println("Invalid details. Update cancelled.");
                    return;
                }

                s.setName(name);
                s.setAge(age);
                s.setCourse(course);

                saveStudents();
                System.out.println("Student updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Delete a student by ID
    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = readInt();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                saveStudents();

                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Read an integer safely
    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            sc.next();
        }

        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    // Save student records to a text file
    static void saveStudents() {
        File folder = new File("data");

        if (!folder.exists() && !folder.mkdirs()) {
            System.out.println("Unable to create data folder.");
            return;
        }

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter("data/students.txt"))) {

            for (Student s : students) {
                writer.println(
                        s.getId() + "|" +
                        s.getName() + "|" +
                        s.getAge() + "|" +
                        s.getCourse()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving student records: " + e.getMessage());
        }
    }

    // Load student records when the program starts
    static void loadStudents() {
        File file = new File("data/students.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] details = line.split("\\|", -1);

                if (details.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(details[0]);
                    String name = details[1];
                    int age = Integer.parseInt(details[2]);
                    String course = details[3];

                    if (id <= 0 || age <= 0 || name.isBlank()
                            || course.isBlank()) {
                        continue;
                    }

                    boolean duplicate = false;

                    for (Student s : students) {
                        if (s.getId() == id) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (!duplicate) {
                        students.add(
                                new Student(id, name, age, course)
                        );
                    }

                } catch (NumberFormatException e) {
                    System.out.println(
                            "Skipping an invalid student record."
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading student records: " + e.getMessage());
        }
    }
}
