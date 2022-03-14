package uz.logistics.ecourier.entity;

import lombok.*;
import uz.logistics.ecourier.entity.mapped.Auditable;

import javax.persistence.*;

import static uz.logistics.ecourier.constant.TableNames.TB_PROPERTY;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TB_PROPERTY)
public class Property extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key", nullable = false, unique = true)
    private String key;

    @Column(name = "value")
    private String value;
}
