package uz.logistics.ecourier.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.logistics.ecourier.service.PropertyService;

@Component
public class EcurierBot extends TelegramLongPollingBot {
    private final PropertyService propertyService;

    public EcurierBot(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public String getBotUsername() {
        return propertyService.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return propertyService.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
