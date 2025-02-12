package com.servinetcomputers.api.module.user.domain.dto;

public record UserFullNameDto(int id, String name, String lastName) {
    public String fullName() {
        return name.concat(" ").concat(lastName);
    }
}
