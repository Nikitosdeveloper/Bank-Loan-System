package com.busir.gardarian.bankloansystem.dao.repositoriy.mapping;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.ClientAdditionalInfoDB;
import com.busir.gardarian.bankloansystem.dao.repositoriy.models.UserDB;
import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientAdditionalInfoMapper {
    private final EntityManager em;

    public List<ClientAdditionalInfo> toEntity(List<ClientAdditionalInfoDB> clientAdditionalInfoDB){
        return clientAdditionalInfoDB.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<ClientAdditionalInfoDB> toDB(List<ClientAdditionalInfo> clientAdditionalInfo){
        return clientAdditionalInfo.stream()
                .map(this::toDB)
                .toList();
    }

    public ClientAdditionalInfo toEntity(ClientAdditionalInfoDB clientAdditionalInfoDB) {
        if(clientAdditionalInfoDB == null)
            return null;

        ClientAdditionalInfo clientAdditionalInfo = new ClientAdditionalInfo();

        clientAdditionalInfo.setId(Long.valueOf(clientAdditionalInfoDB.getId()));
        clientAdditionalInfo.setUserId(Long.valueOf(clientAdditionalInfoDB.getUserD().getId()));
        clientAdditionalInfo.setAge(clientAdditionalInfoDB.getAge());
        clientAdditionalInfo.setMonthlyIncome(clientAdditionalInfoDB.getMonthlyIncome());
        clientAdditionalInfo.setOtherIncome(clientAdditionalInfoDB.getOtherIncome());
        clientAdditionalInfo.setTotalIncome(clientAdditionalInfoDB.getTotalIncome());

        return clientAdditionalInfo;
    }

    public ClientAdditionalInfoDB toDB(ClientAdditionalInfo clientAdditionalInfo) {
        if(clientAdditionalInfo == null)
            return null;

        ClientAdditionalInfoDB clientAdditionalInfoDB = new ClientAdditionalInfoDB();

        if (clientAdditionalInfo.getId() != null)
            clientAdditionalInfoDB.setId(Math.toIntExact(clientAdditionalInfo.getId()));

        if(clientAdditionalInfo.getUserId() != null){
            UserDB userDB = em.getReference(UserDB.class, clientAdditionalInfo.getUserId());
            clientAdditionalInfoDB.setUserD(userDB);
        }

        clientAdditionalInfoDB.setAge(clientAdditionalInfo.getAge());
        clientAdditionalInfoDB.setMonthlyIncome(clientAdditionalInfo.getMonthlyIncome());
        clientAdditionalInfoDB.setOtherIncome(clientAdditionalInfo.getOtherIncome());
        clientAdditionalInfoDB.setTotalIncome(clientAdditionalInfo.getTotalIncome());

        return clientAdditionalInfoDB;
    }
}
