package com.servinetcomputers.api.module.user.domain.adapter;

public interface UserPersistenceAdapter {
    void savePassword(String userCode, String password);

    boolean existsByCode(String userCode);
}
