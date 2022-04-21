package uz.logistics.ecourier.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.repackaged.org.apache.commons.collections4.map.HashedMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Map;

import static uz.logistics.ecourier.util.ErrorUtil.getStacktrace;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final ObjectMapper objectMapper;

    public LoggingAspect(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Pointcut("@annotation(uz.logistics.ecourier.aop.Logging)")
    public void pointcut(){
        // body of the was left empty, because it does not have functionality other thank marking
    }

    @Around("pointcut()")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        final StopWatch stopWatch = new StopWatch();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String method = signature.getMethod().getName();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        Long executedTime = stopWatch.getTotalTimeMillis();
        logger.info("method: {} executed in {} ms", method, executedTime);
        return proceed;
    }

    @Before("pointcut()")
    public void logMethod(JoinPoint joinPoint){
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            Map<String, Object> parameters = getParameters(joinPoint);
            String method = signature.getMethod().getName();
            String parametersAsString = objectMapper.writeValueAsString(parameters);
            logger.info("Method: {}, arguments: {}", method, parametersAsString);
        } catch (JsonProcessingException e) {
            logger.error("LoggingAspect.logMethod -> stacktrace: {}", getStacktrace(e));
        }
    }

    @AfterReturning(pointcut = "pointcut()", returning = "returned")
    public void logMethodAfter(JoinPoint joinPoint, Object returned){
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String method = signature.getMethod().getName();
            String returnedAsString = objectMapper.writeValueAsString(returned);
            logger.info("Method: {}, returned object: {}", method, returnedAsString);
        } catch (JsonProcessingException e) {
            logger.error("LogAspect.logMethodAfter -> stacktrace: {}", getStacktrace(e));
        }
    }

    private Map<String, Object> getParameters(JoinPoint joinPoint){
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        Map<String, Object> parameters = new HashedMap<>();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            parameters.put(parameterNames[i], joinPoint.getArgs()[i]);
        }
        return parameters;
    }
}
