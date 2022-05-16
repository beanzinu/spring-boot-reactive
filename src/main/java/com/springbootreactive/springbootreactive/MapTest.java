package com.springbootreactive.springbootreactive;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
public class MapTest {
    public static void main(String[] args) {

        String[][] namesArray = new String[][]{
                {"kim", "taeng"}, {"mad", "play"},
                {"kim", "mad"}, {"taeng", "play"}};

                Arrays.stream(namesArray)
                .flatMap(innerArray -> Arrays.stream(innerArray))
                .forEach(System.out::println);
    }
}
