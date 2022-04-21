package uz.logistics.ecourier.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.logistics.ecourier.aop.Logging;
import uz.logistics.ecourier.bot.EcurierBot;
import uz.logistics.ecourier.common.Generated;

@Generated
@Component
public class DispatchHandler {
    private static final Logger logger = LoggerFactory.getLogger(DispatchHandler.class);

    private final EcurierBot ecurierBot;

    public DispatchHandler(@Lazy EcurierBot ecurierBot) {
        this.ecurierBot = ecurierBot;
    }

    @Async
    @Logging
    public void dispatch(Update update){
        if (update.hasMessage() && update.getMessage().hasText()){
            try {
                String messageContent = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();
                SendMessage message = SendMessage.builder()
                        .chatId(chatId.toString())
                        .text(messageContent)
                        .build();
                ecurierBot.execute(message);
            } catch (TelegramApiException e) {
                logger.error("DispatchHandler.dispatch: {}", e.getMessage());
            }
            throw new RuntimeException("Internal error!");
        }
    }
}
