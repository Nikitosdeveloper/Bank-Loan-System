package com.busir.gardarian.bankloansystem.dao.repositoriy.mapping;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.CreditPolicyDB;
import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface CreditPolicyMapper {

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "localDateTimeToTimestamp")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "localDateTimeToTimestamp")
    @Mapping(target = "scoringRules", source = "scoringRules", qualifiedByName = "mapToJsonString")
    CreditPolicies toEntity(CreditPolicyDB creditPolicyDB);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "timestampToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "timestampToLocalDateTime")
    @Mapping(target = "scoringRules", source = "scoringRules", qualifiedByName = "jsonStringToMap")
    CreditPolicyDB toDB(CreditPolicies creditPolicy);

    List<CreditPolicies> toEntity(List<CreditPolicyDB> creditPolicies);
    List<CreditPolicyDB> toDB(List<CreditPolicies> creditPolicies);

    @Named("localDateTimeToTimestamp")
    default Timestamp localDateTimeToTimestamp(LocalDateTime dateTime) {
        return dateTime == null ? null : Timestamp.valueOf(dateTime);
    }

    @Named("timestampToLocalDateTime")
    default LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }

    @Named("mapToJsonString")
    default String mapToJsonString(Map<String, Object> map) {
        if (map == null) return null;
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert map to JSON", e);
        }
    }

    @Named("jsonStringToMap")
    default Map<String, Object> jsonStringToMap(String json) {
        if (json == null || json.isBlank()) return null;
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(json, new com.fasterxml.jackson.core.type.TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to map", e);
        }
    }
}