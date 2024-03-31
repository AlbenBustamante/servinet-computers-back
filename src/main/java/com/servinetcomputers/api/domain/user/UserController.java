package com.servinetcomputers.api.domain.user;

import com.servinetcomputers.api.domain.dashboard.abs.IDashboardService;
import com.servinetcomputers.api.domain.dashboard.dto.DashboardResponse;
import com.servinetcomputers.api.domain.user.abs.IUserService;
import com.servinetcomputers.api.domain.user.model.dto.UserRequest;
import com.servinetcomputers.api.domain.user.model.dto.UserResponse;
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
    private final IDashboardService dashboardService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> get(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable("userId") int userId, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }

    @GetMapping("/{userId}/reports")
    public ResponseEntity<DashboardResponse> getReports(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(dashboardService.getReports(userId));
    }

}
