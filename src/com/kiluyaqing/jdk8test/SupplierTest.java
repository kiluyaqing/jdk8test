package com.kiluyaqing.jdk8test;

import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();
    }
}
