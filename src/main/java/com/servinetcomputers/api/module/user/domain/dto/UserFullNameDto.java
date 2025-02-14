package com.servinetcomputers.api.module.user.domain.dto;

public record UserFullNameDto(String name, String lastName) {
    public String fullName() {
        return name.concat(" ").concat(lastName);
    }
}
