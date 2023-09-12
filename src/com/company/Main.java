package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Run and time method
        long startTime = System.nanoTime();
        int result = problem5(20);
        long endTime = System.nanoTime();
//        System.out.println(result);
        System.out.println(String.format("Result: %d", result));

        double durationSeconds = (endTime - startTime) / 1e9;
        String isSuccess = durationSeconds <= 60 ? "success!" : "fail";
        System.out.println(String.format("Ran in %.2f seconds, %s", durationSeconds, isSuccess));
    }

    // Returns true iff s is a palindrome, i.e. reads the same backwards and forwards
    // One and zero character strings are degenerate palindromes
    private static boolean isPalindrome(String s) {
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
    private static int lcm(int a, int b) {
        int currAMultiple = a;
        int currBMultiple = b;
        while (currAMultiple != currBMultiple) {
            if (currAMultiple > currBMultiple) currBMultiple += b;
            else currAMultiple += a;
        }
        return currAMultiple;
    }

    private static int problem1(int n) {
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                counter += i;
            }
        }

        return counter;
    }

    private static int problem2(int n) {
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

    private static int problem3(long n) {
        boolean[] possiblePrimes = new boolean[(int)Math.sqrt(n)];
        Arrays.fill(possiblePrimes, true);

        long currentNum = n;
        int sieveIndex = -1;
        int maxPrime = 1;
        int i;

        while (currentNum > 1 && sieveIndex < possiblePrimes.length-1) {
            boolean runLoop = true;
            i = 0;
            // i is the array index, i+2 is the actual factor
            while (runLoop) {
                if (possiblePrimes[i]) {
                    int factor = i + 2;
                    if (i > sieveIndex) {
                        // We haven't gotten this far in a prior run
                        for (int j = factor * 2; j <= possiblePrimes.length; j += factor) {
                            // Set all of factors of this prime to false
                            possiblePrimes[j - 2] = false;
                        }
                    }
                    if (currentNum % factor == 0) {
                        // factor is a prime factor of n
                        currentNum /= factor;
                        if (factor > maxPrime) maxPrime = factor;
                        runLoop = false;
                    }
                }

                if (i == possiblePrimes.length - 1) {
                    // We've reached the end
                    runLoop = false;
                }

                if (i > sieveIndex) sieveIndex = i;
                i++;
            }
        }

        if (currentNum > 1 && currentNum > maxPrime) return (int)currentNum;

        return maxPrime;
    }

    private static int problem4(int maxNum) {
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
        } while (!isPalindrome(Integer.toString(currProduct)));

        int largestPalindrome = currProduct;

        // Find all palindromes where both numbers are greater than the min of leftNum and rightNum
        int minNum = Math.min(leftNum, rightNum);
        int currLeftNum = minNum;
        while (currLeftNum <= maxNum) {
            int currRightNum = minNum;
            while (currRightNum <= maxNum) {
                currProduct = currLeftNum * currRightNum;
                if (isPalindrome(Integer.toString(currProduct)) && currProduct > largestPalindrome) {
                    largestPalindrome = currProduct;
                }
                currRightNum++;
            }
            currLeftNum++;
        }

        return largestPalindrome;
    }

    private static int problem5(int maxNum) {
        int currProduct = maxNum;
        int currNum = maxNum - 1;
        while (currNum > 0) {
            currProduct = lcm(currProduct, currNum);
            currNum--;
        }
        return currProduct;
    }
}
