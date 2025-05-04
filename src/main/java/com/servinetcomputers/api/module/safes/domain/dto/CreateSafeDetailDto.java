package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.module.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateSafeDetailDto {
    private int safeId;
    private BaseDto initialBase, finalBase;
    private SafeDto safe;
}
