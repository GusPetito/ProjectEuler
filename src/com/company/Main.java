package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Run and time method
        long startTime = System.nanoTime();
        int result = problem3(600851475143L);
        long endTime = System.nanoTime();
        System.out.println(String.format("Result: %d", result));

        double durationSeconds = (endTime - startTime) / 1e9;
        String isSuccess = durationSeconds <= 60 ? "success!" : "fail";
        System.out.println(String.format("Ran in %.2f seconds, %s", durationSeconds, isSuccess));
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
}
