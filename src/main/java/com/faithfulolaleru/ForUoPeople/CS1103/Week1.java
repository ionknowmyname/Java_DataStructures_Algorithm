package com.faithfulolaleru.ForUoPeople.CS1103;

import java.util.*;

public class Week1 {

    // private static Scanner scanner = new Scanner(System.in);
    // private static String text;
    // Map<Character, Integer> charFrequency = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        Map<Character, Integer> charFrequency = new HashMap<>();

        System.out.println("Enter your text for analysis:");
        text = scanner.nextLine().trim();

        if (text.isEmpty()) {
            System.out.println("Error: Text cannot be empty.");
            return;
        }

        // Perform all analyses
        displayCharacterCount(text);
        displayWordCount(text);
        displayMostCommonCharacter(text, charFrequency);
        displayCharacterFrequency(scanner, charFrequency);
        displayWordFrequency(text, scanner);
        displayUniqueWordCount(text);
    }

    private static void displayCharacterCount(String text) {
        int count = text.length();
        System.out.println("\nTotal number of characters: " + count);
    }

    private static void displayWordCount(String text) {
        String[] words = text.split("\\s+");
        System.out.println("Total number of words: " + words.length);
    }

    private static void displayMostCommonCharacter(String text, Map<Character, Integer> charFrequency) {

        for (char c : text.toLowerCase().toCharArray()) {
            if (c == ' ') continue;
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Find character with maximum frequency
        char mostCommon = ' ';
        int maxFrequency = 0;

        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostCommon = entry.getKey();
            }
        }

        System.out.println("Most common character: '" + mostCommon + "' (appears " + maxFrequency + " times)");
    }

    private static void displayCharacterFrequency(Scanner scanner, Map<Character, Integer> charFrequency) {
        System.out.println("\nEnter a character to check its frequency:");
        char searchChar = scanner.nextLine().toLowerCase().charAt(0);

        if (charFrequency.containsKey(searchChar)) {
            System.out.println("The character '" + searchChar + "' appears " + charFrequency.get(searchChar) + " times");
        } else {
            System.out.println("The character '" + searchChar + "' does not appear in the text");
        }
    }

    private static void displayWordFrequency(String text, Scanner scanner) {
        System.out.println("\nEnter a word to check its frequency:");
        String searchWord = scanner.nextLine().toLowerCase().trim();

        String[] words = text.toLowerCase().split("\\s+");
        int frequency = 0;

        for (String word : words) {
            if (word.equals(searchWord)) {
                frequency++;
            }
        }

        System.out.println("The word '" + searchWord + "' appears " + frequency + " times");
    }

    private static void displayUniqueWordCount(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        System.out.println("Number of unique words: " + uniqueWords.size());
    }

}
