package com.servinetcomputers.api.domain.safes;

import com.servinetcomputers.api.audit.AuditAuditable;
import com.servinetcomputers.api.audit.AuditSafeBase;
import com.servinetcomputers.api.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.domain.safes.util.SafeConstants.BASE_LENGTH;

@Entity
@Table(name = "safes")
@EntityListeners(value = {AuditSafeBase.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class Safe extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safe_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "SMALLINT")
    private Integer numeral;

    @Column(nullable = false, length = BASE_LENGTH)
    private String initialBase;

    @Column(nullable = false, length = BASE_LENGTH)
    private String finalBase;

}
