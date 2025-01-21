package com.servinetcomputers.api.domain.user;

import com.servinetcomputers.api.domain.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.reports.abs.IReportsService;
import com.servinetcomputers.api.domain.reports.dto.ReportsResponse;
import com.servinetcomputers.api.domain.user.abs.IUserService;
import com.servinetcomputers.api.domain.user.dto.UserRequest;
import com.servinetcomputers.api.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The user's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final IUserService userService;
    private final CashRegisterDetailRepository cashRegisterDetailService;
    private final IReportsService reportsService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> get(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @GetMapping("/{userId}/reports/cash-register-details")
    public ResponseEntity<MyCashRegistersReports> getCashRegisterReports(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(cashRegisterDetailService.getReportsByUserId(userId));
    }

    @GetMapping("/{code}/reports")
    public ResponseEntity<ReportsResponse> getReports(@PathVariable("code") String code) {
        return ResponseEntity.ok(reportsService.getReports(code));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable("userId") int userId, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }

}
