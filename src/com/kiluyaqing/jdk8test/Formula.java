package com.kiluyaqing.jdk8test;

interface IFormula {

    double caculate(int a);
    
    default double sqrt(int a) {
        return caculate(a);
    }
}

public class Formula {
    
    public static void main(String[] args) {
        IFormula formula = new IFormula() {
            
            @Override
            public double caculate(int a) {
                return a * 100;
            }
        };
        
        System.out.println(formula.caculate(100));
        System.out.println(formula.sqrt(16));
    }
}
