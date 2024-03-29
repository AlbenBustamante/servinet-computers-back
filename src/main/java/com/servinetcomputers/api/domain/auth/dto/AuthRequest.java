package com.servinetcomputers.api.domain.auth.dto;

/**
 * Authentication dto model for request.
 */
public record AuthRequest(String username, String password) {
}
