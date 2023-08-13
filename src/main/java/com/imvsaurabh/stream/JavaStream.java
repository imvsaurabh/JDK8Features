package com.imvsaurabh.stream;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : saurabh
 * @create : 13-08-2023 13:00
 **/

public class JavaStream {

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> randomNums = random.ints(20, 1, 20).boxed().toList();
        System.out.println("Original List : " + randomNums);

        List<? extends Serializable> objLst = Arrays.asList(5, "RAM", 8.0, "null", "Ravi", null);

//        Find duplicate numbers from the list [Sol 1]
        List<Integer> duplicateNums1 = randomNums.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey).toList();
        System.out.println("Duplicate Number List : " + duplicateNums1);

//        Find duplicate numbers from the list [Sol 2]
        Set<Integer> duplicateNums2 = randomNums.stream()
                .filter(num -> Collections.frequency(randomNums, num) > 1)
                .collect(Collectors.toSet());
        System.out.println("Duplicate Number List : " + duplicateNums2);

//        Find unique numbers using stream
        List<Integer> uniqueNums = randomNums.stream().distinct().toList();
        System.out.println("Unique Number List : " + uniqueNums);

//        Find even unique number
        List<Integer> evenNums = randomNums.stream()
                .filter(num -> num % 2 == 0)
                .distinct().toList();
        System.out.println("Even Number List : " + evenNums);

//        Check if any number in between 5 & 9
        boolean isNumFoundBetween5and9 = randomNums.stream()
                .anyMatch(num -> num > 5 && num < 9);
        System.out.println("isNumFoundBetween5and9 : " + isNumFoundBetween5and9);

//        Check if objLst contains any null value
        boolean isContainsNull = objLst.stream()
                .anyMatch(Objects::isNull);
        System.out.println("objLst isContainsNull : " + isContainsNull);

    }

}
