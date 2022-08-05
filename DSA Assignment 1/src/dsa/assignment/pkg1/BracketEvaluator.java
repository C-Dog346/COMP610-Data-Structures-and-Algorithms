/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.assignment.pkg1;

import java.util.Stack;

/**
 *
 * @author Callum
 */
public class BracketEvaluator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String string1 = "aaaa{}";
        String string2 = "a(()(2)q<wgg><a<{aaadasd]aaa{}";
                
        try {
            bracketMatcher(string1);
            System.out.println("First string all good! :)");
            bracketMatcher(string2);
            System.out.println("Second string all good! :)");
        }
        catch (Exception RuntimeException) {
            System.err.println(RuntimeException);  
        }
    }   
    
    public static void bracketMatcher(String string) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(' || string.charAt(i) == '{' || string.charAt(i) == '[' || string.charAt(i) == '<')
                stack.push(string.charAt(i));
            else if (string.charAt(i) == ')' || string.charAt(i) == '}' || string.charAt(i) == ']' || string.charAt(i) == '>') {
                
                if (stack.isEmpty())
                    throw new RuntimeException("Closing bracket without opening bracket");
                
                switch (string.charAt(i)) {
                    case ')':
                        if (stack.peek().equals('(')) {
                            stack.pop();
                            break;
                        }
                    case '}':
                        if (stack.peek().equals('{')) {
                            stack.pop();
                            break;
                        }
                    case ']':
                        if (stack.peek().equals('[')) {
                            stack.pop();
                            break;
                        }
                    case '>':
                        if (stack.peek().equals('<')) {
                            stack.pop();
                            break;
                        }
                    default:
                        throw new RuntimeException("Bracket mismatch");
                }   
            }
        }
        if (!stack.isEmpty())
            throw new RuntimeException("Opening bracket without closing bracket");
    }
}