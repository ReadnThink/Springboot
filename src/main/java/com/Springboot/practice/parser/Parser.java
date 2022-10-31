package com.Springboot.practice.parser;

public interface Parser<T>{
    T parse(String str);
}
