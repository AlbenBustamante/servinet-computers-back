package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.entity.SafeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafeDetailRepository extends JpaRepository<SafeDetail, Integer> {

}
