package com.kiluyaqing.jdk8test;

public class Lambda {
    
    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        
        converter = Integer::valueOf;
        
        Integer converted = converter.convert("123");
        
        System.out.println(converted);
        
        /**
         * 方法引用
         */
        Converter<String, Boolean> converter2 = "abcde"::startsWith;
        
        System.out.println(converter2.convert("a"));
        
        /**
         * 构造函数引用
         */
        PersonFactory<Person> personFactory = Person::new;
        
        Person person = personFactory.create("Perter", "Parker");
        
        System.out.println(person);
        
        /**
         * 访问局部变量
         */
        final int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        
        System.out.println(stringConverter.convert(2));
        
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

/**
 * 访问对象字段与静态字段
 */
class Lambda2 {
    static int outerStaticNum;
    int outerNum;
    
    @SuppressWarnings("unused")
    void testScopes() {
        
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 24;
            return String.valueOf(from);
        };
    }
    
}

