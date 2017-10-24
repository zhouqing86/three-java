package spi.impl;

import spi.HelloInterface;

public class TextHello implements HelloInterface{

    @Override
    public void sayHello() {
        System.out.println("Text Hello.");
    }
}
