package uz.logistics.ecourier.service

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Shared
import spock.lang.Specification
import uz.logistics.ecourier.constant.enums.Lang
import uz.logistics.ecourier.entity.Message
import uz.logistics.ecourier.repository.MessageRepository

import static java.nio.charset.Charset.forName

class MessageServiceSpec extends Specification{
    private MessageService messageService
    private MessageRepository messageRepository = Mock()

    @Shared
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(42)
            .charset(forName("UTF-8"))
            .stringLengthRange(16, 20)
            .randomizationDepth(2)
            .build()

    def setup(){
        messageService = new MessageService(messageRepository)
    }

    def "get message by key and language success"(){
        given:
        def message = random.nextObject(Message)
        def key = "ecourier.success"
        def lang = Lang.ENG
        message.key = key

        when:
        def actual = messageService.getMessage(lang, key)

        then:
        1 * messageRepository.findByKeyAndLang(key, lang) >> Optional.of(message)
        actual == message.message
    }
}
