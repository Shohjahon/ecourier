package uz.logistics.ecourier.entity;

import lombok.*;
import uz.logistics.ecourier.constant.enums.Lang;
import uz.logistics.ecourier.entity.mapped.Auditable;

import javax.persistence.*;

import static uz.logistics.ecourier.constant.IndexNames.CHAT_ID_INDEX;
import static uz.logistics.ecourier.constant.TableNames.TB_USER;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TB_USER, indexes = {@Index(name = CHAT_ID_INDEX, columnList = "chat_id", unique = true)},
       uniqueConstraints = {@UniqueConstraint(columnNames = {"chat_id", "phone_number"})})
public class User extends Auditable {
    @Transient
    private static final String USER_SEQUENCE_NAME = "user_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_SEQUENCE_NAME)
    @SequenceGenerator(name = USER_SEQUENCE_NAME, sequenceName = USER_SEQUENCE_NAME)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "lang")
    @Enumerated(EnumType.STRING)
    private Lang lang;

    @Column(name = "chat_id")
    private String chatId;
}
