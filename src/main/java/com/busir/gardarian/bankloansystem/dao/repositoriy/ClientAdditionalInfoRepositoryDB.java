package com.busir.gardarian.bankloansystem.dao.repositoriy;

import com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces.ClientAdditionalInfoRepositoryJPA;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.ClientAdditionalInfoMapper;
import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import com.busir.gardarian.bankloansystem.service.interfaces.ClientAdditionalInfoRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Profile("devDAO")
@Primary

public class ClientAdditionalInfoRepositoryDB implements ClientAdditionalInfoRepositoryImpl {
    private final ClientAdditionalInfoRepositoryJPA repository;
    private final ClientAdditionalInfoMapper mapper;

    @Override
    @Transactional
    public ClientAdditionalInfo getClientAdditionalInfoById(Long id) {
        ClientAdditionalInfo obj = mapper.toEntity(repository.findById(id).orElse(null));
        return obj;
    }

    @Override
    @Transactional
    public ClientAdditionalInfo getClientAdditionalInfoByUserId(Long id) {
        ClientAdditionalInfo obj = mapper.toEntity(repository.getByUserD_Id(id).orElse(null));
        return obj;
    }

    @Override
    @Transactional
    public ClientAdditionalInfo saveClientAdditionalInfo(ClientAdditionalInfo clientAdditionalInfo) {
        ClientAdditionalInfo obj = mapper.toEntity(repository.save(mapper.toDB(clientAdditionalInfo)));
        return obj;
    }

    @Override
    @Transactional
    public ClientAdditionalInfo updateClientAdditionalInfo(ClientAdditionalInfo clientAdditionalInfo) {
        ClientAdditionalInfo obj = mapper.toEntity(repository.save(mapper.toDB(clientAdditionalInfo)));
        return obj;
    }

    @Override
    public Boolean deleteClientAdditionalInfo(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
