package com.kaushikam.aspect.domain.model.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class UserAspect {

    private static Logger logger = LoggerFactory.getLogger("UserAspect");

    @Before("execution(* com.kaushikam.aspect.domain.model.User.getName())")
    public void beforeGetName(JoinPoint joinPoint) {
        logger.info("Calling getName method to retrieve name from profile");
    }
}
