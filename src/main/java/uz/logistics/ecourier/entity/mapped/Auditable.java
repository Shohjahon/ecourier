package uz.logistics.ecourier.entity.mapped;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@JsonIgnoreProperties(
        value = {"createdBy", "lastModifiedBy",
                 "createdTime", "updatedTime"},
        allowGetters = true
)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
    @CreatedBy
    @Column(name = "created_by", columnDefinition = "bigint default 1", updatable = false)
    protected Long createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "bigint default 1")
    protected Long lastModifiedBy;

    @Column(name = "created_time", updatable = false)
    private Long createdTime;

    @Column(name = "updatedTime")
    private Long updatedTime;

    @PrePersist
    public void setCreatedTime() { // skipcq: JAVA-E1014
        this.createdTime = System.currentTimeMillis();
        this.updatedTime = System.currentTimeMillis();
    }

    @PreUpdate
    public void setUpdatedTime(){ // skipcq: JAVA-E1014
        this.updatedTime = System.currentTimeMillis();
    }
}
