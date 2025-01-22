package com.servinetcomputers.api.domain.user.controller;

import com.servinetcomputers.api.domain.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.reports.abs.IReportsService;
import com.servinetcomputers.api.domain.reports.dto.ReportsResponse;
import com.servinetcomputers.api.domain.user.application.usecase.DeleteUserUseCase;
import com.servinetcomputers.api.domain.user.application.usecase.GetAllUsersUseCase;
import com.servinetcomputers.api.domain.user.application.usecase.GetUserUseCase;
import com.servinetcomputers.api.domain.user.application.usecase.UpdateUserUseCase;
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
    private final CashRegisterDetailRepository cashRegisterDetailService;
    private final IReportsService reportsService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(getAllUsersUseCase.call());
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> get(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(getUserUseCase.call(userId));
    }

    @GetMapping(path = "/{userId}/reports/cash-register-details")
    public ResponseEntity<MyCashRegistersReports> getCashRegisterReports(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(cashRegisterDetailService.getReportsByUserId(userId));
    }

    @GetMapping(path = "/{code}/reports")
    public ResponseEntity<ReportsResponse> getReports(@PathVariable("code") String code) {
        return ResponseEntity.ok(reportsService.getReports(code));
    }

    @PatchMapping
    public ResponseEntity<UserResponse> update(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserUseCase.call(updateUserDto));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") int userId) {
        deleteUserUseCase.call(userId);
        return ResponseEntity.noContent().build();
    }
}
