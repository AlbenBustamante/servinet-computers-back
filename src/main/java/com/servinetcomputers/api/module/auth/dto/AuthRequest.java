package com.servinetcomputers.api.module.auth.dto;

/**
 * Authentication dto model for request.
 */
public record AuthRequest(String code, String password) {
}
