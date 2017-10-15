package com.hillel.springboot.school.aspects;

import com.hillel.springboot.school.model.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class AspectsExample {

    Logger logger = LoggerFactory.getLogger(AspectsExample.class);

    @Before("execution(java.util.List<com.hillel.springboot.school.model.Student>" +
            " com.hillel.springboot.school.controller.StudentController.getAll())")
    public void beforeGetAllStudents(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature().toString());
        logger.info("We are before getting all students");
    }

    @After("execution(* com.hillel.springboot.school.controller.StudentController.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature().toString());
        logger.info("We are after getting all students");
    }

    @AfterThrowing(pointcut = "execution(* com.hillel.springboot.school.controller.StudentController.*(..))",
            throwing = "exception")
    public void testException(RuntimeException exception) {
        logger.info("after throwing");
    }

    @AfterReturning(pointcut = "execution(* com.hillel.springboot.school.controller.StudentController.*(..))",
            returning = "students")
    public void testReturning(List<Student> students) {
        logger.info(students.toString());
    }

    @Around("execution(* com.hillel.springboot.school.controller.StudentController.*(..))")
    public Object testAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("In around advice");

        try {
            return proceedingJoinPoint.proceed();
        } catch (RuntimeException e) {
            logger.info("got an exception", e);
            return null;
        }
    }

    @Before("bean(*Controller) || bean(*Repository)")
    public void testSpringBean() {
        logger.info("before any mehtod in bean");
    }

    @After("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
    public void afterAnnotatedMethod(JoinPoint joinPoint) {
        logger.info("after annotation");
    }

    @After("execution(* (@org.springframework.web.bind.annotation.RestController *).*(..))")
    public void afterAnnotatedMethodInAllClass(JoinPoint joinPoint) {
        logger.info("after annotation for controller");
    }

    @After("PointcutTemplates.controllersAndServices()")
    public void afterAllControllers() {
        logger.info("after all controllers and repositories");
    }
}
