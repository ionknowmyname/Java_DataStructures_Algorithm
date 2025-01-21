package com.faithfulolaleru.ForUoPeople;

import java.util.Scanner;

public class Week1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Array of questions
        String[] questions = {
            "First Question?\nA. Option 1\nB. Option 2\nC. Option 3\nD. Option 4",
            "Second Question?\nA. Option 1\nB. Option 2\nC. Option 3\nD. Option 4",
            "Third Question?\nA. Option 1\nB. Option 2\nC. Option 3\nD. Option 4",
            "Fourth Question?\nA. Option 1\nB. Option 2\nC. Option 3\nD. Option 4",
            "Fifth Question?\nA. Option 1\nB. Option 2\nC. Option 3\nD. Option 4"
        };

        // Array of correct answers
        char[] answers = {'A', 'B', 'C', 'D', 'A'};

        int score = 0;  // quiz result

        // Loop through each question
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            char userAnswer = Character.toUpperCase(scanner.next().charAt(0));

            // Validate user input
            while (userAnswer < 'A' || userAnswer > 'D') {
                System.out.println("Invalid input. Please enter A, B, C, or D.");
                System.out.print("Your answer: ");
                userAnswer = Character.toUpperCase(scanner.next().charAt(0));
            }

            // Compare user answer with the correct answer
            if (userAnswer == answers[i]) {
                score++;
            }
        }

        // Calculate and display the final score as a percentage
        double percentage = ((double) score / questions.length) * 100;
        System.out.println("You got " + score + " out of " + questions.length + " correct.");
        System.out.printf("Your final score is: %.2f%%\n", percentage);

        // Close the scanner
        scanner.close();
    }
}
