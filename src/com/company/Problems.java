package com.company;

import java.util.Arrays;
import java.util.Map;

public class Problems {
    public static int problem1(int n) {
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                counter += i;
            }
        }

        return counter;
    }

    public static int problem2(int n) {
        int num1 = 1;
        int num2 = 2;
        int sum = num2;
        int newNum = 3;
        while (newNum <= n) {
            if (newNum % 2 == 0) sum += newNum;
            num1 = num2;
            num2 = newNum;
            newNum = num1 + num2;
        }

        return sum;
    }

    public static int problem3(long n) {
        int maxPrime = 1;
        long currentNum = n;

        // Get array of primes
        boolean[] possiblePrimes = Helpers.sieveOfEratosthenes((int)Math.sqrt(n));

        int i = 0;
        while (currentNum > 1 && i < possiblePrimes.length) {
            // i is the index for possiblePrimes array, i + 2 is the actual number
            i = 0;
            boolean runLoop = true;
            while (runLoop) {
                if (possiblePrimes[i]) {
                    // prime is a prime number
                    int prime = i + 2;
                    if (currentNum % prime == 0) {
                        // prime is a prime factor of n
                        // Divide it out of currentNum and compare it to maxPrime
                        if (prime > maxPrime) maxPrime = prime;
                        currentNum /= prime;

                        // Set runLoop to false so we look for primes from the beginning
                        runLoop = false;
                    }
                }
                i++;
            }
        }

        return maxPrime;

    }

    public static int problem4(int maxNum) {
        // Find ~a~ palindrome
        int leftNum = maxNum + 1;
        int rightNum = maxNum;
        int currProduct;
        do  {
            if (leftNum > 1) {
                leftNum--;
            } else {
                leftNum = maxNum;
                rightNum--;
            }
            currProduct = leftNum * rightNum;
        } while (!Helpers.isPalindrome(Integer.toString(currProduct)));

        int largestPalindrome = currProduct;

        // Find all palindromes where both numbers are greater than the min of leftNum and rightNum
        int minNum = Math.min(leftNum, rightNum);
        int currLeftNum = minNum;
        while (currLeftNum <= maxNum) {
            int currRightNum = minNum;
            while (currRightNum <= maxNum) {
                currProduct = currLeftNum * currRightNum;
                if (Helpers.isPalindrome(Integer.toString(currProduct)) && currProduct > largestPalindrome) {
                    largestPalindrome = currProduct;
                }
                currRightNum++;
            }
            currLeftNum++;
        }

        return largestPalindrome;
    }

    // Recursion-esque
    public static int problem5(int maxNum) {
        int currProduct = maxNum;
        int currNum = maxNum - 1;
        while (currNum > 0) {
            currProduct = Helpers.lcm(currProduct, currNum);
            currNum--;
        }
        return currProduct;
    }

    // Brute force
    public static int problem6(int maxNum) {
        int result = ((maxNum+1) * maxNum / 2);
        result = result * result;
        for (int i = 1; i <= maxNum; i++) {
            result -= i * i;
        }
        return result;
    }
}
