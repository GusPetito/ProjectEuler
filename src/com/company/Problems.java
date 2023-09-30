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

    public static int problem11() {
        String grid = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\n" +
                "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\n" +
                "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\n" +
                "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\n" +
                "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\n" +
                "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\n" +
                "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\n" +
                "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\n" +
                "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\n" +
                "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\n" +
                "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\n" +
                "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\n" +
                "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\n" +
                "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\n" +
                "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\n" +
                "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\n" +
                "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\n" +
                "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\n" +
                "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\n" +
                "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";

        // Turn grid string into 2D array
        // Assuming every row is the same length
        String[] rows = grid.split("\n");
        int rowLength = rows[0].split(" ").length;
        int columnLength = rows.length;
        int[][] gridArray = new int[columnLength][rowLength];
        for (int i = 0; i < columnLength; i++) {
            String[] currRow = rows[i].split(" ");
            for (int j = 0; j < rowLength; j++) {
                gridArray[i][j] = Integer.parseInt(currRow[j]);
            }
        }

        // Loop through grid to find max
        int maxProduct = 0;
        for (int i = 0; i < columnLength; i++) {
            for (int j = 0; j < rowLength; j++) {
                // Find horizontal product
                int horizontalProduct = 0;
                if (j <= rowLength - 4) {
                    horizontalProduct = gridArray[i][j + 0] * gridArray[i][j + 1] * gridArray[i][j + 2] * gridArray[i][j + 3];
                }

                // Find vertical product
                int verticalProduct = 0;
                if (i <= columnLength - 4) {
                    verticalProduct = gridArray[i][j] * gridArray[i + 1][j] * gridArray[2][j] * gridArray[i + 3][j];
                }

                // Find negative slope diagonal product
                int diagonalProductNegative = 0;
                if (j <= columnLength - 4 && i <= rowLength - 4) {
                    diagonalProductNegative =
                            gridArray[i + 0][j + 0] * gridArray[i + 1][j + 1] * gridArray[i + 2][j + 2] * gridArray[i + 3][j + 3];
                }

                // Find positive slope diagonal product
                int diagonalProductPositive = 0;
                if (j <= columnLength - 4 && i >= 3) {
                    diagonalProductPositive =
                            gridArray[i - 0][j + 0] * gridArray[i - 1][j + 1] * gridArray[i - 2][j + 2] * gridArray[i - 3][j + 3];
                }

                // Find max product
                maxProduct = Math.max(maxProduct,
                        Math.max(horizontalProduct,
                                Math.max(verticalProduct,
                                        Math.max(diagonalProductNegative, diagonalProductPositive))));
            }
        }

        return maxProduct;
    }
}
