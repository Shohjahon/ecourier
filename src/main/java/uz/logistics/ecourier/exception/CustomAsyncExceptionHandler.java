package uz.logistics.ecourier.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.logistics.ecourier.bot.MonitoringBot;
import uz.logistics.ecourier.service.PropertyService;

import java.io.StringWriter;
import java.lang.reflect.Method;

import static uz.logistics.ecourier.util.ErrorUtil.getStacktrace;

@Component
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);

    private final MonitoringBot monitoringBot;
    private final ObjectMapper objectMapper;
    private final PropertyService propertyService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public CustomAsyncExceptionHandler(MonitoringBot monitoringBot,
                                       ObjectMapper objectMapper,
                                       PropertyService propertyService) {
        this.monitoringBot = monitoringBot;
        this.objectMapper = objectMapper;
        this.propertyService = propertyService;
    }

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        var exception = getStacktrace(ex);
        var methodName = method.getName();

        StringBuilder content = new StringBuilder();
        content.append("<bold>E-Courier: Async error details</bold>");
        content.append(String.format("profile: %s", activeProfile));
        content.append(String.format("method: %s", methodName));
        content.append(String.format("stacktrace: %s", exception));

        var message = new SendMessage();
        message.enableHtml(true);
        message.setChatId(propertyService.getMonitoringChatId());

        try {
            StringWriter sw = new StringWriter();
            objectMapper.writeValue(sw, params);
            var parameters = sw.toString();
            content.append(String.format("parameters: %s", parameters));
            message.setText(content.toString());
            monitoringBot.execute(message);
        } catch (Exception e) {
            logger.error("CustomAsyncExceptionHandler.handleUncaughtException: failed", e);
        }
    }
}
