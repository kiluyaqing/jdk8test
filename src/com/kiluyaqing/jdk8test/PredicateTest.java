package com.kiluyaqing.jdk8test;

import java.util.Objects;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;
        
        System.out.println(predicate.test("abc"));
        System.out.println(predicate.negate().test("abc"));
        
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
}
