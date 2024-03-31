package com.servinetcomputers.api.domain.platform;

import com.servinetcomputers.api.domain.platform.abs.IPlatformService;
import com.servinetcomputers.api.domain.platform.model.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.model.dto.PlatformResponse;
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

import java.util.List;

/**
 * The platform's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/platforms")
@RestController
public class PlatformController {

    private final IPlatformService platformService;

    @PostMapping
    public ResponseEntity<PlatformResponse> register(@RequestBody PlatformRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platformService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PlatformResponse>> getAll() {
        return ResponseEntity.ok(platformService.getAll());
    }

    @PatchMapping("/{platformId}")
    public ResponseEntity<PlatformResponse> update(@PathVariable("platformId") int platformId, @RequestBody PlatformRequest request) {
        return ResponseEntity.ok(platformService.update(platformId, request));
    }

    @DeleteMapping("/{platformId}")
    public ResponseEntity<Boolean> delete(@PathVariable("platformId") int platformId) {
        return ResponseEntity.ok(platformService.delete(platformId));
    }

}
