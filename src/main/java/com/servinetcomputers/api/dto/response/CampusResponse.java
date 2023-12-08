package com.servinetcomputers.api.dto.response;

import lombok.Getter;

import java.util.List;

/**
 * The campus dto model for responses.
 */
@Getter
public class CampusResponse extends ModelResponse {
    private final int numeral;
    private final String address, cellphone, terminal;
    private final List<PlatformResponse> platforms;

    public CampusResponse(int id, int numeral, String address, String cellphone, List<PlatformResponse> platforms,
                          String terminal, boolean isAvailable, String createdAt, String updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.numeral = numeral;
        this.address = address;
        this.cellphone = cellphone;
        this.platforms = platforms;
        this.terminal = terminal;
    }
}
