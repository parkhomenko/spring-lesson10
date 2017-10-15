package com.hillel.springboot.school.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutTemplates {

    @Pointcut("bean(*Controller) || bean(*Repository)")
    public void controllersAndServices() {
    }
}
