package uz.logistics.ecourier.service;

import org.springframework.stereotype.Service;
import uz.logistics.ecourier.common.Generated;
import uz.logistics.ecourier.entity.Property;
import uz.logistics.ecourier.repository.PropertyRepository;

import static uz.logistics.ecourier.constant.PropertyKeys.BOT_TOKEN;
import static uz.logistics.ecourier.constant.PropertyKeys.BOT_USERNAME;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public String getValue(final String key){
        return propertyRepository.findByKey(key)
                .map(Property::getValue)
                .orElse("");
    }

    @Generated
    public String getBotUsername(){
        return getValue(BOT_USERNAME);
    }

    @Generated
    public String getBotToken(){
        return getValue(BOT_TOKEN);
    }
}
