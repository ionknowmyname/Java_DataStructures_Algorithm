package com.faithfulolaleru.HACKERRANK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class QueueUsingStacks {

    Stack<Integer> input;
    Stack<Integer> output;

    public QueueUsingStacks() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(String x) {
        input.push(Integer.valueOf(x));
    }

    public String pop() {
        if (output.isEmpty()) {   // only populate from input to output if output is empty
            while (!input.isEmpty()) output.push(input.pop());
        }
        return output.pop().toString();
    }

    public String peek() {
        if(!output.empty()) {
            return output.peek().toString();
        } else {
            while (!input.isEmpty()) output.push(input.pop());
        }
        return output.peek().toString();
    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        try{
            QueueUsingStacks queue = new QueueUsingStacks();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int numberOfOperations = Integer.parseInt(reader.readLine());
            for (int i = 0; i < numberOfOperations; i++){
                String[] command = reader.readLine().split("\\s");  // split by space

                switch (command[0]) {
                    case "1":
                        queue.push(command[1]);
                        break;
                    case "2":
                        queue.pop();
                        break;
                    case "3":
                        System.out.println(queue.peek());
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
