package com.faithfulolaleru.HACKERRANK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SimpleTextEditor {



    public static void main(String[] args) {

        try {
            StringBuilder S = new StringBuilder();
            Stack<String> prevStrings = new Stack<>();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int numberOfOperations = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numberOfOperations; i++) {
                String[] command = reader.readLine().split("\\s");  // split by space

                switch (command[0]) {
                    case "1":
                        // append to string
                        prevStrings.push(S.toString());
                        S.append(command[1]);
                        break;
                    case "2":
                        // delete last Y characters
                        prevStrings.push(S.toString());

                        int Y = Integer.parseInt(command[1]);
                        if (Y >= S.length()) {
                            S.setLength(0);  // to delete more than there is, just delete the whole thing
                        } else {
                            S.setLength(S.length() - Y);
                        }

                        break;
                    case "3":
                        // print Kth character
                        int K = Integer.parseInt(command[1]);
                        if (K > 0 && K <= S.length()) {
                            System.out.println(S.charAt(K - 1));
                        }

                        break;
                    case "4":
                        // undo is only for case 1 and 2
                        if (!prevStrings.isEmpty()) {
                            S = new StringBuilder(prevStrings.pop());
                        }

                        break;
                    default:
                        throw new Exception("Invalid input!");
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



    public static void myOriginalApproach() {
        try {
            String S = "";
            String temp = "";
            // int prevCommand = 0; // append = 1; delete = -1
            Stack<Integer> prevCommands = new Stack<>();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int numberOfOperations = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numberOfOperations; i++) {
                String[] command = reader.readLine().split("\\s");  // split by space

                switch (command[0]) {
                    case "1":
                        // append to string
                        S += command[1];
                        temp = command[1];  // save what you adding temporarily
                        prevCommands.push(1);
                        break;
                    case "2":
                        // delete last k characters
                        int index = S.length() - Integer.parseInt(command[1]);  // -1
                        // S = S.substring(0, index + 1);
                        String temp2 = S;  // store original S temporarily before messing with it
                        S = S.substring(0, index);
                        temp = temp2.substring(index); // save what you deleting temporarily too
                        // prevCommand = -1;
                        prevCommands.push(-1);
                        break;
                    case "3":
                        // print Kth character
                        System.out.println(S.charAt(Integer.parseInt(command[1]) - 1));
                        break;
                    case "4":
                        // undo is only for case 1 and 2
                        int prevCommand = prevCommands.pop();
                        if (prevCommand == 1) {
                            // remove what you added that's in temp from S
                            int tempLength = temp.length();
                            int finalLength = S.length() - tempLength;
                            S = S.substring(0, finalLength);
                            temp = "";
                        }

                        if (prevCommand == -1) {
                            // add back what you removed that's in temp to S
                            S += temp;
                            temp = "";
                        }
                        break;
                    default:
                        throw new Exception("Invalid input!");
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
