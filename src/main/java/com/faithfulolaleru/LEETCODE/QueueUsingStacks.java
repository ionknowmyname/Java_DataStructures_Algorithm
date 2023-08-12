package com.faithfulolaleru.LEETCODE;

import java.util.Stack;

public class QueueUsingStacks {

    // 232. Implement Queue using Stacks

    /*
    *   two stacks, input stack takes all the push, bt the last in is on top, and the first in is at the bottom
    *   & to process queue, the first in is first out, so we pop one by one from input stack to output stack,
    *   so in output stack, the first one to be pushed into queue is actually the one on top now & can be popped off
    *   when dequeuing. If any new pushes, push to input stack so we don't mess up output stack orderliness for
    *   popping/dequeing from queue. Only when output stack is empty do you now load the new pushes in input stack to
    *   output stack, so they ordered once again for dequeing
    *
    *
    * */

    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    public QueueUsingStacks() {

    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        if (output.isEmpty()) {   // only populate from input to output if output is empty
            while (!input.isEmpty()) output.push(input.pop());
        }
        return output.pop();
    }

    public int peek() {
        if(!output.empty()) {
            return output.peek();
        } else {
            while (!input.isEmpty()) output.push(input.pop());
        }
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();  // both must be empty before queue is trully empty
    }
}
