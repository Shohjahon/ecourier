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
    @Transient
    private static final String PROP_SEQUENCE_NAME = "property_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROP_SEQUENCE_NAME)
    @SequenceGenerator(name = PROP_SEQUENCE_NAME, sequenceName = PROP_SEQUENCE_NAME)
    private Long id;

    @Column(name = "key", nullable = false, unique = true)
    private String key;

    @Column(name = "value")
    private String value;
}
