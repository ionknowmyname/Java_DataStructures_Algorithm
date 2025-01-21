package com.faithfulolaleru.ForUoPeople;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Week8 {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
            new Employee("Test1", 33, "Test6", 60000),
            new Employee("Test2", 28, "Test7", 75000),
            new Employee("Test3", 35, "Test8", 90000),
            new Employee("Test4", 40, "Test9", 85000),
            new Employee("Test5", 25, "Test10", 55000)
        );

        Function<Employee, String> nameAndDept = emp -> emp.getName() + " - " + emp.getDepartment();

        List<String> nameAndDeptList = employees.stream()
                .map(nameAndDept)
                // .map(emp -> emp.getName() + " - " + emp.getDepartment())  // either-or
                .collect(Collectors.toList());

        System.out.println("Name and Department List:");
        nameAndDeptList.forEach(System.out::println);

        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.println("Average Salary: $" + averageSalary);

        // employees above minimum age
        int minAge = 32;
        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.getAge() > minAge)
                .collect(Collectors.toList());

        System.out.println("Employees above age " + minAge + ":");
        filteredEmployees
            .forEach(emp -> System.out.println(emp.getName() + " - " + emp.getAge() + " years"));

        // average salary of employees above minAge
        double averageSalaryAboveMinAge = employees.stream()
                .filter(emp -> emp.getAge() > minAge)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.printf("Average Salary of Employees Above %d: %.2f%n", minAge, averageSalaryAboveMinAge);
    }

    static class Employee {
        private String name;
        private int age;
        private String department;
        private double salary;

        public Employee(String name, int age, String department, double salary) {
            this.name = name;
            this.age = age;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }
}
