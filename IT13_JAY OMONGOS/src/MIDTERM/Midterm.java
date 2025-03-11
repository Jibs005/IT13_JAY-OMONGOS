package Midterm;

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your text: ");
            String inputText = scanner.nextLine();
            
            String reversedText = new StringBuilder(inputText).reverse().toString();
            
            if (isPalindrome(inputText, reversedText)) {
                System.out.println("The text is a palindrome.");
            } else {
                System.out.println("The text is not a palindrome.");
            }
        }
    }
    
    public static boolean isPalindrome(String original, String reversed) {
        
        original = original.replaceAll("\\s", "").toLowerCase();
        reversed = reversed.replaceAll("\\s", "").toLowerCase();
                
        return original.equals(reversed);
    }
}