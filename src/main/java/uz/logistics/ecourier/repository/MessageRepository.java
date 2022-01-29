package uz.logistics.ecourier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.logistics.ecourier.constant.enums.Lang;
import uz.logistics.ecourier.entity.Message;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findByKeyAndLang(String key, Lang lang);

    Optional<Message> findByMessageAndLang(String message, Lang lang);

    Optional<Message> findByMessage(String message);
}
