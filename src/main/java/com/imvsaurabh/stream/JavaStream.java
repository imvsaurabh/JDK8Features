package com.imvsaurabh.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.frequency;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toSet;

/**
 * @author : saurabh
 * @create : 13-08-2023 13:00
 **/

public class JavaStream {

    private static final Logger log = LoggerFactory.getLogger(JavaStream.class);

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> randomNums = random.ints(20, 1, 20).boxed().toList();
        log.info("Original List : {}", randomNums);

        List<? extends Serializable> objLst = Arrays.asList(5, "RAM", 8.0, "null", "Ravi", null);

//        Find duplicate numbers from the list [Sol 1]
        List<Integer> duplicateNums1 = randomNums.stream()
                .collect(Collectors.groupingBy(identity(), counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey).toList();
        log.info("Duplicate Number List : {}", duplicateNums1);

//        Find duplicate numbers from the list [Sol 2]
        Set<Integer> duplicateNums2 = randomNums.stream()
                .filter(num -> frequency(randomNums, num) > 1)
                .collect(toSet());
        log.info("Duplicate Number List : {}", duplicateNums2);

//        Find unique numbers using stream
        List<Integer> uniqueNums = randomNums.stream().distinct().toList();
        log.info("Unique Number List : {}", uniqueNums);

//        Find even unique number
        List<Integer> evenNums = randomNums.stream()
                .filter(num -> num % 2 == 0)
                .distinct().toList();
        log.info("Even Number List : {}", evenNums);

//        Check if any number in between 5 & 9
        boolean isNumFoundBetween5and9 = randomNums.stream()
                .anyMatch(num -> num > 5 && num < 9);
        log.info("isNumFoundBetween5and9 : {}", isNumFoundBetween5and9);

//        Check if objLst contains any null value
        boolean isContainsNull = objLst.stream()
                .anyMatch(Objects::isNull);
        log.info("objLst isContainsNull : {}", isContainsNull);

//        Maximum number
        Integer maxNum = randomNums.stream().max(Comparator.naturalOrder()).orElse(0);
        log.info("Maximum number from the randomNums : {}", maxNum);

//        Minimum number
        Integer minNum = randomNums.stream().min(Comparator.naturalOrder()).orElse(0);
        log.info("Minimum number from the randomNums : {}", minNum);

//        Average number
        Integer sumNum = randomNums.stream().reduce(Integer::sum).orElse(0);
        log.info("Average number from the randomNums : {}", sumNum / randomNums.size());

//        Sum of all digit of a number
        int digitSum = Arrays.stream(String.valueOf(sumNum).split(""))
                .mapToInt(Integer::parseInt).sum();
        log.info("Sum of digits of number {} is {}", sumNum, digitSum);

//        Second smallest number
        Integer secondSmallestNumber = randomNums.stream().distinct().sorted().skip(1).findFirst().orElse(0);
        log.info("2nd smallest number from the randomNums : {}", secondSmallestNumber);

//        Second largest number
        Integer secondLargestNumber = randomNums.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0);
        log.info("2nd largest number from the randomNums : {}", secondLargestNumber);


    }
}
