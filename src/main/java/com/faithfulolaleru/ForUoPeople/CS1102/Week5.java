package com.faithfulolaleru.ForUoPeople.CS1102;

import java.util.*;
import java.util.stream.Collectors;

public class Week5 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grade");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    AdminInterface.addCourse();
                    break;
                case 2:
                    AdminInterface.enrollStudent();
                    break;
                case 3:
                    AdminInterface.assignGrade();
                    break;
                case 4:
                    AdminInterface.calculateOverallGrade();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static class Course {
        private String courseCode;
        private String name;
        private int maxCapacity;
        private int currentEnrollment;

        public Course(String courseCode, String name, int maxCapacity) {
            this.courseCode = courseCode;
            this.name = name;
            this.maxCapacity = maxCapacity;
            this.currentEnrollment = 0;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getName() {
            return name;
        }

        public int getMaxCapacity() {
            return maxCapacity;
        }

        public int getCurrentEnrollment() {
            return currentEnrollment;
        }

        public void incrementEnrollment() {
            currentEnrollment++;
        }
    }

    static class Student {
        private String name;
        private String id;
        private List<Course> enrolledCourses;
        private Map<Course, Double> grades;

        public Student(String name, String id) {
            this.name = name;
            this.id = id;
            this.enrolledCourses = new ArrayList<>();
            this.grades = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Course> getEnrolledCourses() {
            return enrolledCourses;
        }

        public void enrollInCourse(Course course) {
            if (course.getCurrentEnrollment() < course.getMaxCapacity()) {
                enrolledCourses.add(course);
                course.incrementEnrollment();
            } else {
                System.out.println("Cannot enroll in course " + course.getCourseCode() + ". Maximum capacity reached.");
            }
        }

        public void assignGrade(Course course, double grade) {
            if (enrolledCourses.contains(course)) {
                grades.put(course, grade);
            } else {
                System.out.println("Student is not enrolled in the course " + course.getCourseCode());
            }
        }

        public double calculateOverallGrade() {
            double totalGrades = 0;
            for (double grade : grades.values()) {
                totalGrades += grade;
            }
            return totalGrades / grades.size();
        }
    }

    static class CourseManagement {
        // all courses available
        private static List<Course> courses = new ArrayList<>();

        // all courses registered per student
        private static Map<Student, List<Course>> studentCourses = new HashMap<>();

        // all courses with the result registered per student
        private static Map<Student, Map<Course, Double>> studentGrades = new HashMap<>();

        public static void addCourse(String courseCode, String name, int maxCapacity) {
            Course newCourse = new Course(courseCode, name, maxCapacity);
            courses.add(newCourse);
        }

        public static List<Course> getCourses() {
            return courses;
        }

        public static void enrollStudent(Student student, Course course) {
            student.enrollInCourse(course);
            studentCourses.computeIfAbsent(student, k -> new ArrayList<>()).add(course);
        }

        public static List<Student> getStudents() {
            return studentCourses.keySet().stream().collect(Collectors.toList());
        }

        public static void assignGrade(Student student, Course course, double grade) {
            student.assignGrade(course, grade);
            studentGrades.computeIfAbsent(student, k -> new HashMap<>()).put(course, grade);
        }

        public static double calculateOverallGrade(Student student) {
            return student.calculateOverallGrade();
        }
    }

    static public class AdminInterface {

        private static void addCourse() {
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine();
            System.out.print("Enter course name: ");
            String name = scanner.nextLine();
            System.out.print("Enter course maximum capacity: ");
            int maxCapacity = scanner.nextInt();
            scanner.nextLine();  // consume newline

            CourseManagement.addCourse(courseCode, name, maxCapacity);
            System.out.println("Course added successfully.");
        }

        private static void enrollStudent() {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();
            Student student = new Student(name, id);

            System.out.print("Enter course code to enroll in: ");
            String courseCode = scanner.nextLine();
            Course course = findCourseByCode(courseCode);

            if (course != null) {
                CourseManagement.enrollStudent(student, course);
                System.out.println("Student enrolled successfully.");
            } else {
                System.out.println("Course not found.");
            }
        }

        private static void assignGrade() {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();
            Student student = findStudentById(id);

            if (student != null) {
                System.out.print("Enter course code: ");
                String courseCode = scanner.nextLine();
                Course course = findCourseByCode(courseCode);

                if (course != null) {
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine();  // consume newline

                    CourseManagement.assignGrade(student, course, grade);
                    System.out.println("Grade assigned successfully.");
                } else {
                    System.out.println("Course not found.");
                }
            } else {
                System.out.println("Student not found.");
            }
        }

        private static void calculateOverallGrade() {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();
            Student student = findStudentById(id);

            if (student != null) {
                double overallGrade = CourseManagement.calculateOverallGrade(student);
                System.out.println("Overall grade for student " + student.getName() + ": " + overallGrade);
            } else {
                System.out.println("Student not found.");
            }
        }

        private static Course findCourseByCode(String courseCode) {
            for (Course course : CourseManagement.getCourses()) {
                if (course.getCourseCode().equals(courseCode)) {
                    return course;
                }
            }
            return null;
        }

        private static Student findStudentById(String id) {
            for (Student student : CourseManagement.getStudents()) {
                if (student.getId().equals(id)) {
                    return student;
                }
            }
            return null;
        }
    }

}
