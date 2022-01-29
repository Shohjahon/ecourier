package uz.logistics.ecourier.service;

import org.springframework.stereotype.Service;
import uz.logistics.ecourier.constant.enums.Lang;
import uz.logistics.ecourier.entity.Message;
import uz.logistics.ecourier.repository.MessageRepository;

import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String getMessage(Lang lang, String key){
        return messageRepository.findByKeyAndLang(key, lang)
                .map(Message::getMessage)
                .orElse(key);
    }

    public String getKeyByMessage(String message){
        return messageRepository.findByMessage(message)
                .map(Message::getKey)
                .orElse(message);
    }

    public Optional<Lang> getLangByMessage(String message){
        return messageRepository.findByMessage(message)
                .map(Message::getLang);
    }
}
