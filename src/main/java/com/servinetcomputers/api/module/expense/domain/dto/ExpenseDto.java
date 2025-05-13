package com.servinetcomputers.api.module.expense.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseDto extends AuditableDto<Integer> {
    private Integer cashRegisterDetailId, value;
    private String description;
    private Boolean discount, administrative;
    private CashRegisterDetailDto cashRegisterDetail;

    public ExpenseDto copy() {
        final var dto = new ExpenseDto(
                cashRegisterDetailId,
                value,
                description,
                discount,
                administrative,
                cashRegisterDetail
        );

        dto.setId(super.getId());
        dto.setEnabled(super.getEnabled());
        dto.setCreatedBy(super.getCreatedBy());
        dto.setCreatedDate(super.getCreatedDate());
        dto.setModifiedBy(super.getModifiedBy());
        dto.setModifiedDate(super.getModifiedDate());

        return dto;
    }
}
