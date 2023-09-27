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

    // Solved by incrementally doubling sieve length
    public static int problem7(int n) {
        // If numPrime is x, then the current prime is the xth prime
        int numPrime = 0;
        boolean[] primeArray = new boolean[0];
        int sieveLength = n;
        int i = 0;

        while (true) {
            // We haven't found the nth prime yet
            // Double length of the sieve and keep searching
            sieveLength *= 2;
            primeArray = Helpers.sieveOfEratosthenes(sieveLength, primeArray);
            for (int j = i; j < primeArray.length; j++) {
                if (primeArray[j]) {
                    numPrime++;
                    if (numPrime == n) {
                        // We've found the nth prime; return it
                        return j + 2;
                    }
                }
            }
            i = primeArray.length;
        }
    }

    // Brute force
    public static long problem8(int n) {
        long maxProduct = -1;
        String digits = "73167176531330624919225119674426574742355349194934" +
                "96983520312774506326239578318016984801869478851843" +
                "85861560789112949495459501737958331952853208805511" +
                "12540698747158523863050715693290963295227443043557" +
                "66896648950445244523161731856403098711121722383113" +
                "62229893423380308135336276614282806444486645238749" +
                "30358907296290491560440772390713810515859307960866" +
                "70172427121883998797908792274921901699720888093776" +
                "65727333001053367881220235421809751254540594752243" +
                "52584907711670556013604839586446706324415722155397" +
                "53697817977846174064955149290862569321978468622482" +
                "83972241375657056057490261407972968652414535100474" +
                "82166370484403199890008895243450658541227588666881" +
                "16427171479924442928230863465674813919123162824586" +
                "17866458359124566529476545682848912883142607690042" +
                "24219022671055626321111109370544217506941658960408" +
                "07198403850962455444362981230987879927244284909188" +
                "84580156166097919133875499200524063689912560717606" +
                "05886116467109405077541002256983155200055935729725" +
                "71636269561882670428252483600823257530420752963450";

        // Loop through all adjacent digits
        for (int i = 0; i < digits.length() - (n - 1); i++) {
            long currProduct = 1;
            for (int j = 0; j < n; j++) {
                int currDigit = Character.getNumericValue(digits.charAt(j + i));
                currProduct *= currDigit;
            }
            if (currProduct > maxProduct) maxProduct = currProduct;
        }

        return maxProduct;
    }

    // Brute force
    public static int problem9(int sum) {
        int a = 1;
        while (true) {
            // Loop over a
            for (int b = 1; b < sum - a; b++) {
                // Loop over b
                int cSquared = a*a + b*b;
                if (Helpers.isPerfectSquare(cSquared)) {
                    // This is a Pythagorean triplet
                    // We just need to check if it sums up
                    int c = (int)Math.sqrt(cSquared);
                    if (a + b + c == sum) return a * b * c;
                }
            }
            a++;
        }
    }

    public static long problem10(int maxPrime) {
        // If arrayLength is 1, the only number we get is 2
        // which is what we want if maxPrime is 3, for example
        boolean[] primeArray = Helpers.sieveOfEratosthenes(maxPrime - 2);
        long result = 0;
        for (int i = 0; i < primeArray.length; i++) {
            if (primeArray[i]) result += 2 + i;
        }
        return result;
    }
}
