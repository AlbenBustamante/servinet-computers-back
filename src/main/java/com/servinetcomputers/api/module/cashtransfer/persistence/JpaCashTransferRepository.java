package com.servinetcomputers.api.module.cashtransfer.persistence;

import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashtransfer.persistence.entity.CashTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaCashTransferRepository extends JpaRepository<CashTransfer, Integer> {
    Optional<CashTransfer> findByIdAndEnabledTrue(Integer id);

    @Query("SELECT ct FROM CashTransfer ct " +
            "WHERE (ct.senderId = :id AND ct.senderType = :type) " +
            "OR (ct.receiverId = :id AND ct.receiverType = :type) " +
            "AND ct.enabled = true")
    Page<CashTransfer> findAllByCashBoxIdAndTypeAndEnabledTrue(int id, CashBoxType type, Pageable pageable);

    @Query("SELECT ct FROM CashTransfer ct " +
            "WHERE (ct.senderId = :id AND ct.senderType = :type) " +
            "OR (ct.receiverId = :id AND ct.receiverType = :type) " +
            "AND ct.enabled = true")
    List<CashTransfer> findAllByCashBoxIdAndTypeAndEnabledTrue(int id, CashBoxType type);

    @Query("SELECT SUM(ct.value) FROM CashTransfer ct " +
            "WHERE ct.senderId = :senderId " +
            "AND ct.senderType = :senderType " +
            "AND ct.enabled = true")
    Integer sumAllBySenderIdAndType(int senderId, CashBoxType senderType);

    @Query("SELECT SUM(ct.value) FROM CashTransfer ct " +
            "WHERE ct.receiverId = :receiverId " +
            "AND ct.receiverType = :receiverType " +
            "AND ct.enabled = true")
    Integer sumAllByReceiverIdAndType(int receiverId, CashBoxType receiverType);
}
