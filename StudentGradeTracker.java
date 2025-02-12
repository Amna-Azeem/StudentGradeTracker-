import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int[] marks;
    int total;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
        this.total = calculateTotal();
    }

    private int calculateTotal() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        String[] subjects = {"History", "Statistics", "Programming"};

        System.out.println("Student Grade Tracker");
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();

        for (int i = 0; i < numberOfStudents; i++) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            int[] marks = new int[3];
            for (int j = 0; j < 3; j++) {
                System.out.printf("Enter marks for %s: ", subjects[j]);
                marks[j] = scanner.nextInt();
            }

            students.add(new Student(name, marks));
        }

        Student highestTotalStudent = students.get(0);
        Student lowestTotalStudent = students.get(0);

        int[] highestSubjectMarks = new int[3];
        String[] highestSubjectStudents = new String[3];

        for (int i = 0; i < 3; i++) {
            highestSubjectMarks[i] = students.get(0).marks[i];
            highestSubjectStudents[i] = students.get(0).name;
        }

        for (Student student : students) {
            // Check for highest and lowest total marks
            if (student.total > highestTotalStudent.total) {
                highestTotalStudent = student;
            }
            if (student.total < lowestTotalStudent.total) {
                lowestTotalStudent = student;
            }

            // Check for highest marks in each subject
            for (int j = 0; j < 3; j++) {
                if (student.marks[j] > highestSubjectMarks[j]) {
                    highestSubjectMarks[j] = student.marks[j];
                    highestSubjectStudents[j] = student.name;
                }
            }
        }

        System.out.println("\nResults:");
        System.out.printf("%n%-15s %-10s %-10s %-10s %-15s%n", "Student", "History", "Statistics", "Programming", "Total (Highest/Lowest)");
        System.out.println("---------------------------------------------------------------------");

        for (Student student : students) {
            String highestOrLowest = "";
            if (student == highestTotalStudent) {
                highestOrLowest = "(Highest)";
            } else if (student == lowestTotalStudent) {
                highestOrLowest = "(Lowest)";
            }
            System.out.printf("%-15s %-10d %-10d %-10d %-15d %s%n", student.name, student.marks[0], student.marks[1], student.marks[2], student.total, highestOrLowest);
        }

        System.out.println("\nHighest Marks in Each Subject:");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-15s %-10d by %s%n", subjects[i], highestSubjectMarks[i], highestSubjectStudents[i]);
        }

        scanner.close();
    }
}
