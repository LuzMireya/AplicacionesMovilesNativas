package com.samuelbarush.cerradura.model;

import org.springframework.stereotype.Service;

@Service
public class CerraduraService {

    public long getFibonacci (int n){
        if (n <= 1){
            return n;
        }
        long fib = 1, prevFib = 1;
        for(int i = 2; i < n; i++){
            long temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }

    public long getFactorial (int n){
        if (n < 0){
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        long factorial = 1;
        for (int i = 2; i <=n; i++){
            factorial *= i;
        }
        return factorial;
    }
    
}
