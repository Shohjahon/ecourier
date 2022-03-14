package uz.logistics.ecourier.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.logistics.ecourier.service.PropertyService;

@Component
public class MonitoringBot extends TelegramLongPollingBot {
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

    @Override
    public String getBotUsername() {
        return propertyService.getMonitoringBotUsername();
    }

    @Override
    public String getBotToken() {
        return propertyService.getMonitoringBotToken();
    }

}
