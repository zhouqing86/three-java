package spi.impl;

import spi.HelloInterface;

public class ImageHello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Image Hello");
    }
}
