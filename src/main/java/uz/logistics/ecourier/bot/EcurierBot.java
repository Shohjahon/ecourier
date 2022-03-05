package uz.logistics.ecourier.bot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.logistics.ecourier.handler.DispatchHandler;
import uz.logistics.ecourier.service.PropertyService;

import java.util.concurrent.Executor;

@Component
public class EcurierBot extends TelegramLongPollingBot {
    private final PropertyService propertyService;
    private final DispatchHandler dispatchHandler;
    private final Executor executor;


    public EcurierBot(PropertyService propertyService,
                      DispatchHandler dispatchHandler,
                      @Qualifier("handlerExecutor") Executor executor) {
        this.propertyService = propertyService;
        this.dispatchHandler = dispatchHandler;
        this.executor = executor;
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
