package com.servinetcomputers.api.domain.platform.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.domain.platform.util.PlatformConstants.NAME_LENGTH;

/**
 * The platform's model entity.
 */
@Entity
@Table(name = "platforms")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class Platform extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private Integer id;

    @Column(nullable = false, length = NAME_LENGTH, unique = true)
    private String name;

}
