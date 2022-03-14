package uz.logistics.ecourier.service

import spock.lang.Unroll
import uz.logistics.ecourier.BaseSpecification
import uz.logistics.ecourier.entity.Property
import uz.logistics.ecourier.repository.PropertyRepository

class PropertyServiceSpec extends BaseSpecification {
    private PropertyService propertyService
    private PropertyRepository propertyRepository = Mock()

    def setup(){
        propertyService = new PropertyService(propertyRepository)
    }

    @Unroll
    def "get value: #value by key: #key -> success"(Long id, String key, String value){
        given:
        def property = new Property(id, key, value)

        when:
        def actual = propertyService.getValue(key)

        then:
        1 * propertyRepository.findByKey(key) >> Optional.of(property)
        actual == value

        where:
        id      |   key                     |     value
        1001    | "ecourier.token"          | "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
        2244    | "ecourier.project.name"   | "e-courier"
        3654    | "ecourier.start.date"     | "11.03.2022"
        4760    | "ecourier.bot.username"   | "@ecourierbot"
        2000    | null                      | ""
    }
}