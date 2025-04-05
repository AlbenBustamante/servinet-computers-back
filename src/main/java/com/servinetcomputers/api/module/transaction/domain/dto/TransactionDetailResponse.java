package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
public class TransactionDetailResponse extends ModelResponse {
    private final int cashRegisterDetailId;
    private int value, commission;
    private String description;
    private TransactionDetailType type;
    private LocalDateTime date;
    private TransactionResponse transaction;
    private CashRegisterDetailResponse cashRegisterDetail;

    public TransactionDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                     int cashRegisterDetailId, String description, int value, int commission, TransactionDetailType type, LocalDateTime date) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.cashRegisterDetailId = cashRegisterDetailId;
        this.description = description;
        this.value = value;
        this.commission = commission;
        this.type = type;
        this.date = date;
    }

    public static TransactionDetailResponse copyWith(TransactionDetailResponse response) {
        final var copy = new TransactionDetailResponse(
                response.getId(),
                response.isEnabled(),
                response.getCreatedDate(),
                response.getModifiedDate(),
                response.getCreatedBy(),
                response.getModifiedBy(),
                response.getCashRegisterDetailId(),
                response.getDescription(),
                response.getValue(),
                response.getCommission(),
                response.getType(),
                response.getDate()
        );

        copy.setTransaction(response.getTransaction());
        copy.setCashRegisterDetail(response.getCashRegisterDetail());

        return copy;
    }
}
