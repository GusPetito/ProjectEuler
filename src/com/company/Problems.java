package com.company;

import java.util.Arrays;

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

    public static int problem5(int maxNum) {
        int currProduct = maxNum;
        int currNum = maxNum - 1;
        while (currNum > 0) {
            currProduct = Helpers.lcm(currProduct, currNum);
            currNum--;
        }
        return currProduct;
    }
}
