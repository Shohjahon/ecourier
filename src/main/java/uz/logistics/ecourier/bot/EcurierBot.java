package uz.logistics.ecourier.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.logistics.ecourier.handler.DispatchHandler;
import uz.logistics.ecourier.service.PropertyService;

@Component
public class EcurierBot extends TelegramLongPollingBot {
    private final PropertyService propertyService;
    private final DispatchHandler dispatchHandler;


    public EcurierBot(PropertyService propertyService,
                      DispatchHandler dispatchHandler) {
        this.propertyService = propertyService;
        this.dispatchHandler = dispatchHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        dispatchHandler.dispatch(update);
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
