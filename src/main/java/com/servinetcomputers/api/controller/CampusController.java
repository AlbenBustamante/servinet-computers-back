package com.servinetcomputers.api.controller;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.service.ICampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The campus' routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/campuses")
@RestController
public class CampusController {

    private final ICampusService campusService;

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

}
