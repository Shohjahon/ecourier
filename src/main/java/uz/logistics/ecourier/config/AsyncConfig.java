package uz.logistics.ecourier.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import uz.logistics.ecourier.exception.CustomAsyncExceptionHandler;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig implements AsyncConfigurer {
    private final CustomAsyncExceptionHandler asyncExceptionHandler;

    public AsyncConfig(CustomAsyncExceptionHandler asyncExceptionHandler) {
        this.asyncExceptionHandler = asyncExceptionHandler;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(1000);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("handler-");
        executor.setAwaitTerminationSeconds(3);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncExceptionHandler;
    }
}
