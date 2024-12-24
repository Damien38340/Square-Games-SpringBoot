package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {

        System.out.println("Exercise 1");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenDoubleNumbers = numbers.stream()
                .filter(number -> number % 2 == 0) // Check if the number is even
                .map(number -> number * 2) // double each even number
                .collect(Collectors.toList()); // Collect results in new list


        System.out.println("Evens numbers doubled " + evenDoubleNumbers + "\n");

        System.out.println("________________________");

        System.out.println("Exercise 2");

        List<String> strings = Arrays.asList("The", "owls", "are not", "what they seem");

        long lengthMoreThanFive = strings.stream()
                .filter(s -> s.length()>5)
                .count();

        System.out.println("The number of strings greater than 5 characters are " + lengthMoreThanFive);

        System.out.println("________________________");

        System.out.println("Exercise 3");

        List<String> someStrings = Arrays.asList("Fire", "walk", "with", "me");

        String convertToUpperCaseAndSortAlphabetically = someStrings.stream()
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.joining(" "));

        System.out.println(convertToUpperCaseAndSortAlphabetically);

    }
}
