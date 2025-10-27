package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanPurposeRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanPurposeService {
    private final LoanPurposeRepositoryImpl loanPurposeRepository;

    public List<LoanPurpose> getAll() {
        return loanPurposeRepository.findAll();
    }
}
