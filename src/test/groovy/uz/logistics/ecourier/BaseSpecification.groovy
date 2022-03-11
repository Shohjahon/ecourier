package uz.logistics.ecourier

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Shared
import spock.lang.Specification

import static java.nio.charset.Charset.forName


class BaseSpecification extends Specification {

    @Shared
    protected
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(42)
            .charset(forName("UTF-8"))
            .stringLengthRange(16, 20)
            .randomizationDepth(2)
            .build()
}