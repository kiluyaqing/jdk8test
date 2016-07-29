package com.kiluyaqing.jdk8test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        
        System.out.println(names);
        
        Collections.sort(names, (String a, String b) -> a.compareTo(b));
        Collections.sort(names, (a, b) -> a.compareTo(b));
        
        System.out.println(names);
    }
}
