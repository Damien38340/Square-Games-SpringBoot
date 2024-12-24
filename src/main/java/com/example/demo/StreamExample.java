package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evens = numbers.stream()
                .filter(number -> number % 2 == 0) // Check if the number is even
                .collect(Collectors.toList());

        List<Integer> odds = numbers.stream()
                .filter(number -> number % 2 != 0) // Check if the number is odd
                .collect(Collectors.toList());

        System.out.println("Evens: " + evens);
        System.out.println("Odds: " + odds);
    }
}
