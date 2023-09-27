package com.company;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ProblemsTest {
    // Test all PE problems
    // Typically test sample in problem and actual problem
    // Also make sure all problems run in less than a minute

    // TODO: Fix expected and actual order

    @Test
    void problem1example() {
        assertEquals(Problems.problem1(10), 23);
    }

    @Test
    void problem1() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem1(1000));
        assertEquals(Problems.problem1(1000), 233168);
    }

    @Test
    @Timeout(1)
    void problem2() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem2((int)4e6));
        assertEquals(Problems.problem2((int)4e6), 4613732);
    }

    @Test
    void problem3example() {
        assertEquals(Problems.problem3(13195), 29);
    }

    @Test
    void problem3() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem3(600851475143L));
        assertEquals(Problems.problem3(600851475143L), 6857);
    }

    @Test
    void problem4example() {
        assertEquals(Problems.problem4(99), 9009);
    }

    @Test
    void problem4() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem4(999));
        assertEquals(Problems.problem4(999), 906609);
    }

    @Test
    void problem5example() {
        assertEquals(Problems.problem5(10), 2520);
    }

    @Test
    void problem5() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem5(20));
        assertEquals(Problems.problem5(20), 232792560);
    }

    @Test
    void problem6example() {
        assertEquals(Problems.problem6(10), 2640);
    }

    @Test
    void problem6() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem6(100));
        assertEquals(Problems.problem6(100), 25164150);
    }

    @Test
    void problem7example() {
        assertEquals(Problems.problem7(6), 13);
    }

    @Test
    void problem7() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem7(10001));
        assertEquals(Problems.problem7(10001), 104743);
    }

    @Test
    void problem8example() {
        assertEquals(Problems.problem8(4), 5832);
    }

    @Test
    void problem8() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem8(13));
        assertEquals(Problems.problem8(13), 23514624000L);
    }

    @Test
    void problem9example() {
        assertEquals(Problems.problem9(12), 60);
    }

    @Test
    void problem9() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem9(1000));
        assertEquals(Problems.problem9(1000), 31875000);
    }

    @Test
    void problem10example() {
        assertEquals(Problems.problem10(10), 17);
    }

    @Test
    void problem10() {
        assertTimeoutPreemptively(Duration.ofSeconds(60), () -> Problems.problem10(2000000));
        assertEquals(Problems.problem10(2000000), 142913828922L);
    }
}