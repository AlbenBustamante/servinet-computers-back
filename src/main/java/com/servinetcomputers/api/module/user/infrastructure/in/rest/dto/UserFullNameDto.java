package com.servinetcomputers.api.module.user.infrastructure.in.rest.dto;

public record UserFullNameDto(String name, String lastName) {
    public String fullName() {
        return name.concat(" ").concat(lastName);
    }
}
