package uz.logistics.ecourier.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.logistics.ecourier.service.PropertyService;

@Component
public class MonitoringBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringBot.class);

    private final PropertyService propertyService;

    public MonitoringBot(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        /**
         *  left empty because monitoring bot does not
         *  have any business functionality
         */
    }


    public synchronized void sendAnalyticMessage(final String content){
        try {
            var message = new SendMessage();
            message.enableMarkdown(true);
            message.setChatId(propertyService.getMonitoringChatId());
            message.setText(content);
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("MonitoringBot.sendAnalyticMessage: ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return propertyService.getMonitoringBotUsername();
    }

    @Override
    public String getBotToken() {
        return propertyService.getMonitoringBotToken();
    }

}
