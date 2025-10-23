package com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.ClientAdditionalInfoDB;
import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientAdditionalInfoRepositoryJPA extends JpaRepository<ClientAdditionalInfoDB, Long> {

   Optional<ClientAdditionalInfoDB> getByUserD_Id(Long userId);
}
