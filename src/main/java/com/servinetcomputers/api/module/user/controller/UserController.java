package com.servinetcomputers.api.module.user.controller;

import com.servinetcomputers.api.module.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.module.reports.application.usecase.GetDetailedTransactionsUseCase;
import com.servinetcomputers.api.module.reports.dto.ReportsResponse;
import com.servinetcomputers.api.module.user.application.usecase.DeleteUserUseCase;
import com.servinetcomputers.api.module.user.application.usecase.GetAllUsersUseCase;
import com.servinetcomputers.api.module.user.application.usecase.GetUserCashRegisterReportsUseCase;
import com.servinetcomputers.api.module.user.application.usecase.GetUserUseCase;
import com.servinetcomputers.api.module.user.application.usecase.UpdateUserUseCase;
import com.servinetcomputers.api.module.user.domain.dto.UpdateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final GetDetailedTransactionsUseCase getDetailedTransactionsUseCase;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(getAllUsersUseCase.call());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") int userId) {
        return ResponseEntity.ok(getUserUseCase.call(userId));
    }

    @GetMapping(path = "/{id}/reports/cash-register-details")
    public ResponseEntity<MyCashRegistersReports> getCashRegisterReports(@PathVariable("id") int userId) {
        return ResponseEntity.ok(getReportsUseCase.call(userId));
    }

    @GetMapping(path = "/reports")
    public ResponseEntity<ReportsResponse> getReports() {
        return ResponseEntity.ok(getDetailedTransactionsUseCase.call());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") int id, @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserUseCase.call(id, updateUserDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int userId) {
        deleteUserUseCase.call(userId);
        return ResponseEntity.ok().build();
    }
}
