package org.example.task_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainTask3 {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> numbers = new ArrayList<>();

        intList.forEach(num -> {
            if (num > 0 && num % 2 == 0) numbers.add(num);
        });

        Collections.sort(numbers);
        numbers.forEach(num -> System.out.print(num + " "));
    }
}
