package com.busir.gardarian.bankloansystem.service.stub;

import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanPurposeRepositoryImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("devService")
public class StubLoanPurposeRepository implements LoanPurposeRepositoryImpl {
    @Override
    public List<LoanPurpose> findAll() {
        return List.of(
                new LoanPurpose(
                        1L,
                        "home",
                        "На недвижимость",
                        "Кредит который берется с целью приобретения недвижимости",
                        "real_estate"
                ),
                new LoanPurpose(
                        2L,
                        "car",
                        "На транспортное средство",
                        "Кредит который берётся с целью приобритения машины",
                        "transport"
                )
        );
    }

    @Override
    public LoanPurpose findById(Long id) {
        return new LoanPurpose(
                1L,
                "home",
                "На недвижимость",
                "Кредит который берется с целью приобретения недвижимости",
                "real_estate"
        );
    }

    @Override
    public LoanPurpose findByName(String name) {
        return new LoanPurpose(
                2L,
                "car",
                "На транспортное средство",
                "Кредит который берётся с целью приобритения машины",
                "transport"
        );
    }

    @Override
    public LoanPurpose findByCode(String code) {
        return new LoanPurpose(
                1L,
                "home",
                "На недвижимость",
                "Кредит который берется с целью приобретения недвижимости",
                "real_estate"
        );
    }

    @Override
    public LoanPurpose findByCategory(String category) {
        return new LoanPurpose(
                2L,
                "car",
                "На транспортное средство",
                "Кредит который берётся с целью приобритения машины",
                "transport"
        );
    }

    @Override
    public LoanPurpose save(LoanPurpose loanPurpose) {
        loanPurpose.setId(3L);
        return loanPurpose;
    }

    @Override
    public Boolean delete(Long id) {
        return true;
    }
}
