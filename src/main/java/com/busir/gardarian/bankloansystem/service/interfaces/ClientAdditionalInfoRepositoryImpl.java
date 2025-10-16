package com.busir.gardarian.bankloansystem.service.interfaces;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;

public interface ClientAdditionalInfoRepositoryImpl {
    ClientAdditionalInfo getClientAdditionalInfoById(Long id);
    ClientAdditionalInfo getClientAdditionalInfoByUserId(Long id);
    ClientAdditionalInfo saveClientAdditionalInfo(ClientAdditionalInfo clientAdditionalInfo);
    ClientAdditionalInfo updateClientAdditionalInfo(ClientAdditionalInfo clientAdditionalInfo);
    Boolean deleteClientAdditionalInfo(Long id);
}
