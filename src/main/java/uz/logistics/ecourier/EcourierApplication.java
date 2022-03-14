package uz.logistics.ecourier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class EcourierApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcourierApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(){
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return objectMapper;
    }
}
