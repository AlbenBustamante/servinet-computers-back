package com.servinetcomputers.api.controller;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.request.PageRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.TransferResponse;
import com.servinetcomputers.api.service.ICampusService;
import com.servinetcomputers.api.service.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The campus' routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/campuses")
@RestController
public class CampusController {

    private final ICampusService campusService;
    private final ITransferService transferService;

    @PostMapping
    public ResponseEntity<PageResponse<CampusResponse>> register(@RequestBody CampusRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(campusService.create(request));
    }

    @GetMapping("/{campusId}")
    public ResponseEntity<PageResponse<CampusResponse>> get(@PathVariable("campusId") int campusId) {
        return ResponseEntity.ok(campusService.get(campusId));
    }

    @PatchMapping("/{campusId}")
    public ResponseEntity<PageResponse<CampusResponse>> update(@PathVariable("campusId") int campusId, @RequestBody CampusRequest request) {
        return ResponseEntity.ok(campusService.update(campusId, request));
    }

    @DeleteMapping("/{campusId}")
    public ResponseEntity<Boolean> delete(@PathVariable("campusId") int campusId) {
        return ResponseEntity.ok(campusService.delete(campusId));
    }

    @PostMapping("/{campusId}/platform/{platformName}")
    public ResponseEntity<PageResponse<CampusResponse>> addPlatform(
            @PathVariable("campusId") int campusId,
            @PathVariable("platformName") String platformName
    ) {
        return ResponseEntity.ok(campusService.addPlatform(campusId, platformName));
    }

    @GetMapping(value = "/{campusId}/transfers")
    public ResponseEntity<PageResponse<TransferResponse>> getTransfersByCreationDate(
            @PathVariable("campusId") int campusId,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "createdAt") String property,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate
    ) {
        final var pageRequest = new PageRequest(size, page, direction, property);

        if (startDate == null) {
            startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        }

        if (endDate == null) {
            endDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        }

        return ResponseEntity.ok(transferService.getAllByCampusIdCreationDateBetween(campusId, startDate, endDate, pageRequest));
    }

    @DeleteMapping("/{campusId}/platform/{platformName}")
    public ResponseEntity<PageResponse<CampusResponse>> removePlatform(
            @PathVariable("campusId") int campusId,
            @PathVariable("platformName") String platformName
    ) {
        return ResponseEntity.ok(campusService.removePlatform(campusId, platformName));
    }

}
