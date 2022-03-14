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
@Table(name = TB_USER, uniqueConstraints = {@UniqueConstraint(columnNames = {"chat_id", "phone_number"})})
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "lang")
    @Enumerated(EnumType.STRING)
    private Lang lang;

    @Column(name = "chat_id")
    private String chatId;
}
