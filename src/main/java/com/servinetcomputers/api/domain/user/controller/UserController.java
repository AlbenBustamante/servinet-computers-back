package com.servinetcomputers.api.domain.user.controller;

import com.servinetcomputers.api.domain.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.domain.reports.abs.IReportsService;
import com.servinetcomputers.api.domain.reports.dto.ReportsResponse;
import com.servinetcomputers.api.domain.user.application.usecase.*;
import com.servinetcomputers.api.domain.user.domain.dto.UpdateUserDto;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The user's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@RestController
public class UserController {
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserCashRegisterReportsUseCase getReportsUseCase;
    private final IReportsService reportsService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(getAllUsersUseCase.call());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable("id") int userId) {
        return ResponseEntity.ok(getUserUseCase.call(userId));
    }

    @GetMapping(path = "/{id}/reports/cash-register-details")
    public ResponseEntity<MyCashRegistersReports> getCashRegisterReports(@PathVariable("id") int userId) {
        return ResponseEntity.ok(getReportsUseCase.call(userId));
    }

    @GetMapping(path = "/{code}/reports")
    public ResponseEntity<ReportsResponse> getReports(@PathVariable("code") String code) {
        return ResponseEntity.ok(reportsService.getReports(code));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") int id, @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserUseCase.call(id, updateUserDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int userId) {
        deleteUserUseCase.call(userId);
        return ResponseEntity.ok().build();
    }
}
