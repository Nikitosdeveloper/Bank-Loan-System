package com.busir.gardarian.bankloansystem.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoanPurpose {
    private Long id;
    private String code;
    private String nameRu;
    private String descriptionRu;
    private String category;
}
