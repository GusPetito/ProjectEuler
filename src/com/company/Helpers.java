package com.company;

import java.util.Arrays;
import java.util.stream.Stream;

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

    // sieveOfEratosthenes with no starter array
    public static boolean[] sieveOfEratosthenes(int arrayLength) {
        return sieveOfEratosthenes(arrayLength, new boolean[] {});
    }

    // Return boolean array of length arrayLength
    // arrayLength should be greater than starterArray.length
    // starterArray should be a correct possiblePrimes starter
    // If result[i] is true, i+2 is a prime number
    public static boolean[] sieveOfEratosthenes(int arrayLength, boolean[] starterArray) {
        // Extend starter array
        boolean[] possiblePrimes = new boolean[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            if (i < starterArray.length) possiblePrimes[i] = starterArray[i];
            else possiblePrimes[i] = true;
        }

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

    public static boolean isPerfectSquare(double num) {
        double rootedNum = Math.sqrt(num);
        return Math.floor(rootedNum) == Math.ceil(rootedNum);
    }
}
