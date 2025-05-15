package com.servinetcomputers.api.module.auth.dto;

public record ChangePasswordDto(
        String userCode,
        String tempCode,
        String newPassword,
        String confirmPassword
) {
    public boolean passwordsDoMatch() {
        return newPassword.equals(confirmPassword);
    }
}
