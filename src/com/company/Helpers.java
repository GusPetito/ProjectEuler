package com.company;

public class Helpers {
    // Returns true iff s is a palindrome, i.e. reads the same backwards and forwards
    // One and zero character strings are degenerate palindromes
    public static boolean isPalindrome(String s) {
        int leftCounter = 0;
        int rightCounter = s.length() - 1;
        while (leftCounter < rightCounter) {
            if (s.charAt(leftCounter) != s.charAt(rightCounter)) return false;
            leftCounter++;
            rightCounter--;
        }

        return true;
    }

    // Least common multiple of positive integers a and b
    public static int lcm(int a, int b) {
        int currAMultiple = a;
        int currBMultiple = b;
        while (currAMultiple != currBMultiple) {
            if (currAMultiple > currBMultiple) currBMultiple += b;
            else currAMultiple += a;
        }
        return currAMultiple;
    }
}
