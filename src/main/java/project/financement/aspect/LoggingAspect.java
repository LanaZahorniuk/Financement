package project.financement.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * project.financement.controller.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * project.financement.service.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("NEW REQUEST:\n" +
                        "IP : {}\n" +
                        "URL : {}\n" +
                        "HTTP_METHOD : {}\n" +
                        "CONTROLLER_METHOD : {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint joinPoint) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()

        );
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("\nReturn value: {}\n" +
                        "END OF REQUEST",
                returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }

}
