package com.servinetcomputers.api.domain.transaction.service;

import com.servinetcomputers.api.domain.transaction.abs.ITransactionService;
import com.servinetcomputers.api.domain.transaction.abs.TransactionMapper;
import com.servinetcomputers.api.domain.transaction.abs.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements ITransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllDescriptions() {
        return repository.findAllDescriptions();
    }

}
