package com.company;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n*=3;
        n+=Integer.parseInt("10101",2);
        n+=(new BigInteger("FF",16)).intValue();
        n*=6;
        System.out.println("n="+n);
        int result=n;
        while(result/10>0){
            n=result;
            result=0;
            while(n>0){
                result=result+n%10;
                n/=10;
            }
        }
        System.out.println("result="+result);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
