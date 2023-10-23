package com.servinetcomputers.api.dto.request;

/**
 * The campus dto model for requests.
 */
public record CampusRequest(int userId, int numeral, String address, String cellphone, String password,
                            String repeatPassword) {
    public boolean passwordsMatch() {
        return password.equals(repeatPassword);
    }
}
