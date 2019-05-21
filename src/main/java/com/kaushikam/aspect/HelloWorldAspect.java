package com.kaushikam.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class HelloWorldAspect {
    @After("execution(* com.kaushikam.aspect.HelloWorld.sayHello(..))")
    public void hello() {
        System.out.println(" World!");
    }
}
