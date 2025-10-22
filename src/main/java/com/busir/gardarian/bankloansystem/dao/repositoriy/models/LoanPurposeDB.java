package com.busir.gardarian.bankloansystem.dao.repositoriy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loan_purposes", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "purposes_code", columnNames = {"code"})
})
public class LoanPurposeDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_purposes_id_gen")
    @SequenceGenerator(name = "loan_purposes_id_gen", sequenceName = "loan_purposes_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "name_ru", nullable = false, length = 200)
    private String nameRu;

    @Column(name = "\"description_ru \"", length = Integer.MAX_VALUE)
    private String descriptionRu;

    @Column(name = "\"category \"", nullable = false, length = 50)
    private String category;

}