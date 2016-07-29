package com.kiluyaqing.jdk8test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamTest {

    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2"); 
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        
        /**
         * filter
         */
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        
        /**
         * sort
         */
        stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        
        /**
         * map
         */
        stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
        
        /**
         * match
         */
        boolean anyStratsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
        boolean allStratsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
        boolean noneStratsWithA = stringCollection.stream().noneMatch((s) -> s.startsWith("a"));
        
        System.out.println(anyStratsWithA);
        System.out.println(allStratsWithA);
        System.out.println(noneStratsWithA);
        
        /**
         * count
         */
        long stratsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
        System.out.println(stratsWithB);
        
        /**
         * reduce
         */
        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        
        reduced.ifPresent(System.out::println);
        
        /**
         * 并行stream
         */
        int max = 1000000;
        List<String> values = new ArrayList<String>(max);
        for (int i=0; i<max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        
        long t0 = System.nanoTime();
        
        long count = values.stream().sorted().count();
        System.out.println(count);
        
        long t1 = System.nanoTime();
        
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
        
        t0 = System.nanoTime();
        
        count = values.parallelStream().sorted().count();
        System.out.println(count);
        
        t1 = System.nanoTime();
        
        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
        
        /**
         * map
         */
        Map<Integer, String> map = new HashMap<>();
        
        for (int i=0; i<10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        
        map.forEach((id, val) -> System.out.println(val));
        
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));
        
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));
        
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));
        
        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));
        
        map.remove(3, "val3");
        System.out.println(map.get(3));
        
        map.remove(3, "val33");
        System.out.println(map.get(3));
        
        System.out.println(map.getOrDefault(33, "not fount"));
        
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));
        
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));
        
        
    }
}
