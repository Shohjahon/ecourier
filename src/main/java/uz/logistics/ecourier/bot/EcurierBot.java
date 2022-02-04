package uz.logistics.ecourier.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.logistics.ecourier.service.PropertyService;

@Component
public class EcurierBot extends TelegramLongPollingBot {
    private final Logger logger = LoggerFactory.getLogger(EcurierBot.class);

    private final PropertyService propertyService;

    public EcurierBot(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            try {
                String messageContent = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();
                SendMessage message = SendMessage.builder()
                        .chatId(chatId.toString())
                        .text(messageContent)
                        .build();

                execute(message);
            } catch (TelegramApiException e) {
                logger.error("EcourierBot.onUpdateReceived: {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return propertyService.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return propertyService.getBotToken();
    }

}
