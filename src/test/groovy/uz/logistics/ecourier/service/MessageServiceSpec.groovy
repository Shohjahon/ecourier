package uz.logistics.ecourier.service


import spock.lang.Unroll
import uz.logistics.ecourier.BaseSpecification
import uz.logistics.ecourier.constant.enums.Lang
import uz.logistics.ecourier.entity.Message
import uz.logistics.ecourier.repository.MessageRepository

class MessageServiceSpec extends BaseSpecification{
    private MessageService messageService
    private MessageRepository messageRepository = Mock()

    def setup(){
        messageService = new MessageService(messageRepository)
    }

    @Unroll
    def "get key: #key by message: #message with id: #id -> success"(Long id, String key, String message){
        given:
        def messageEntity = new Message(id, Lang.ENG, key, message)

        when:
        def result = messageService.getKeyByMessage(message)

        then:
        1 * messageRepository.findByMessage(message) >> Optional.of(messageEntity)
        result == key

        where:
        id   |   key                        |   message
        1    | "ecourier.success"           | "Operation is successfully completed"
        2    | "ecourier.fail"              | "Operation failed"
        3    | "ecouier.pending"            | "Operation is in pending state"
        4    | "ecourier.internal.error"    | "Something went wrong"
    }

    def "get lang by by message -> success"(){
        given:
        def messageContent = "Success"
        def message = random.nextObject(Message)
        def lang = Lang.ENG
        message.message = messageContent
        message.lang = lang

        when:
        def actual = messageService.getLangByMessage(messageContent)

        then:
        1 * messageRepository.findByMessage(messageContent) >> Optional.of(message)
        assert actual.get() == lang
    }

    def "get message by key and language ->  success"(){
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
