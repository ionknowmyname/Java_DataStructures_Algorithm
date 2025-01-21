package com.faithfulolaleru.ForUoPeople;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Week3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Student Record Management System");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter student grade (A-F): ");
                    char grade = scanner.next().charAt(0);
                    StudentManagement.addStudent(name, age, grade);
                    break;
                case 2:
                    System.out.print("Enter student ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new student name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new student age: ");
                    int newAge = scanner.nextInt();
                    System.out.print("Enter new student grade (A-F): ");
                    char newGrade = scanner.next().charAt(0);
                    StudentManagement.updateStudent(idToUpdate, newName, newAge, newGrade);
                    break;
                case 3:
                    System.out.print("Enter student ID to view: ");
                    int idToView = scanner.nextInt();
                    StudentManagement.viewStudent(idToView);
                    break;
                case 4:
                    StudentManagement.viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static class Student {
        private String name;
        private int id;
        private int age;
        private char grade;

        public Student(String name, int id, int age, char grade) {
            this.name = name;
            this.id = id;
            this.age = age;
            this.grade = grade;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public char getGrade() {
            return grade;
        }

        public void setGrade(char grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
        }
    }

    static class StudentManagement {
        private static List<Student> students = new ArrayList<>();
        private static int totalStudents = 0;

        public static void addStudent(String name, int age, char grade) {
            int id = totalStudents + 1;
            Student student = new Student(name, id, age, grade);
            students.add(student);
            System.out.println("Student added successfully with ID: " + id);
        }

        public static void updateStudent(int id, String name, int age, char grade) {
            for (Student student : students) {
                if (student.getId() == id) {
                    student.setName(name);
                    student.setAge(age);
                    student.setGrade(grade);
                    System.out.println("Student information updated successfully.");
                    return;
                }
            }
            System.out.println("Error: Student ID not found.");
        }

        public static void viewStudent(int id) {
            for (Student student : students) {
                if (student.getId() == id) {
                    System.out.println(student);
                    return;
                }
            }
            System.out.println("Error: Student ID not found.");
        }

        public static void viewAllStudents() {
            if (students.isEmpty()) {
                System.out.println("No students available.");
            } else {
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        }
    }
}
