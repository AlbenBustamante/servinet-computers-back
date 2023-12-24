package com.servinetcomputers.api.controller;

import com.servinetcomputers.api.dto.request.UserRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.dto.response.DashboardResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.UserResponse;
import com.servinetcomputers.api.service.ICampusService;
import com.servinetcomputers.api.service.IDashboardService;
import com.servinetcomputers.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final IUserService userService;
    private final ICampusService campusService;
    private final IDashboardService dashboardService;

    @PatchMapping("/{userId}")
    public ResponseEntity<PageResponse<UserResponse>> update(@PathVariable("userId") int userId, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }

    @GetMapping("/{userId}/campuses")
    public ResponseEntity<PageResponse<CampusResponse>> getCampuses(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(campusService.getAllByUserId(userId));
    }

    @GetMapping("/{userId}/reports")
    public ResponseEntity<DashboardResponse> getReports(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(dashboardService.getReports(userId));
    }

}
