package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Run and time method
        long startTime = System.nanoTime();
        long result = Problems.problem11();
        long endTime = System.nanoTime();
//        System.out.println(result);
        System.out.println(String.format("Result: %d", result));

        double durationSeconds = (endTime - startTime) / 1e9;
        String isSuccess = durationSeconds <= 60 ? "success!" : "fail";
        System.out.println(String.format("Ran in %.2f seconds, %s", durationSeconds, isSuccess));
    }
}
