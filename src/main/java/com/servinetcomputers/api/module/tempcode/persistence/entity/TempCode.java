package com.servinetcomputers.api.module.tempcode.persistence.entity;

import com.servinetcomputers.api.module.user.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "temp_codes")
@Getter
@Setter
public class TempCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_code_id")
    private Integer id;

    @Column(nullable = false)
    private Integer code;

    @ManyToOne
    @JoinColumn(name = "used_by_id")
    private User usedBy;
}
