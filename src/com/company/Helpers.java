package com.company;

import java.util.Arrays;

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

    // Return boolean array of length arrayLength
    // If result[i] is true, i+2 is a prime number
    public static boolean[] sieveOfEratosthenes(int arrayLength) {
        boolean[] possiblePrimes = new boolean[arrayLength];
        Arrays.fill(possiblePrimes, true);

        // i is the array index of possible primes, i+2 is the actual number
        for (int i = 0; i < arrayLength; i++) {
            if (possiblePrimes[i]) {
                // i+2 is a prime
                int prime = i + 2;
                for (int j = prime * 2; j - 2 < possiblePrimes.length; j += prime) {
                    // Set all of factors of this prime to false
                    possiblePrimes[j - 2] = false;
                }
            }
        }

        return possiblePrimes;
    }
}
