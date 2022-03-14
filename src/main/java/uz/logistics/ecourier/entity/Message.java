package uz.logistics.ecourier.entity;

import lombok.*;
import uz.logistics.ecourier.constant.enums.Lang;
import uz.logistics.ecourier.entity.mapped.Auditable;

import javax.persistence.*;

import static uz.logistics.ecourier.constant.TableNames.TB_MESSAGE;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TB_MESSAGE)
public class Message extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lang")
    @Enumerated(EnumType.STRING)
    private Lang lang;

    @Column(name = "key")
    private String key;

    @Column(name = "message")
    private String message;

}
