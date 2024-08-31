package com.servinetcomputers.api.domain.platform.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * The transfer dto model for requests.
 */
public record PlatformTransferRequest(Integer platformId, Integer value, List<MultipartFile> vouchers) {
}
