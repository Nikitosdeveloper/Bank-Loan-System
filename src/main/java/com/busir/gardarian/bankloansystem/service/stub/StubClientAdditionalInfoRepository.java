package com.busir.gardarian.bankloansystem.service.stub;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import com.busir.gardarian.bankloansystem.service.interfaces.ClientAdditionalInfoRepositoryImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("devService")
public class StubClientAdditionalInfoRepository implements ClientAdditionalInfoRepositoryImpl {
    @Override
    public ClientAdditionalInfo getClientAdditionalInfoById(Long id) {
        return new ClientAdditionalInfo(
                1L,
                1L,
                25,
                new BigDecimal(900),
                new BigDecimal(300),
                new BigDecimal(1200)
        );
    }

    @Override
    public ClientAdditionalInfo getClientAdditionalInfoByUserId(Long id) {
        return new ClientAdditionalInfo(
                1L,
                1L,
                25,
                new BigDecimal(900),
                new BigDecimal(300),
                new BigDecimal(1200)
        );
    }

    @Override
    public ClientAdditionalInfo saveClientAdditionalInfo(ClientAdditionalInfo clientAdditionalInfo) {
        clientAdditionalInfo.setId(1L);
        return clientAdditionalInfo;
    }

    @Override
    public ClientAdditionalInfo updateClientAdditionalInfo(ClientAdditionalInfo clientAdditionalInfo) {
        return clientAdditionalInfo;
    }

    @Override
    public Boolean deleteClientAdditionalInfo(Long id) {
        return false;
    }
}
