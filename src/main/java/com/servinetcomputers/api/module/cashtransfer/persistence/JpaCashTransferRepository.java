package com.servinetcomputers.api.module.cashtransfer.persistence;

import com.servinetcomputers.api.module.cashtransfer.persistence.entity.CashTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCashTransferRepository extends JpaRepository<CashTransfer, Integer> {
    List<CashTransfer> findAllBySenderIdOrReceiverIdAndEnabledTrue(int senderId, int receiverId);

    @Query("SELECT SUM(ct.value) FROM CashTransfer ct " +
            "WHERE ct.senderId = :senderId " +
            "AND ct.enabled = true")
    Integer sumAllBySenderId(int senderId);

    @Query("SELECT SUM(ct.value) FROM CashTransfer ct " +
            "WHERE ct.receiverId = :receiverId " +
            "AND ct.enabled = true")
    Integer sumAllByReceiverId(int receiverId);
}
