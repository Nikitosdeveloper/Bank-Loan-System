package com.busir.gardarian.bankloansystem.dao.repositoriy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "documents", schema = "public")
public class DocumentDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documents_id_gen")
    @SequenceGenerator(name = "documents_id_gen", sequenceName = "documents_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "application_id")
    private LoanApplicationDB application;

    @Column(name = "document_type", length = 50)
    private String documentType;

    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "uploaded_at")
    private LocalTime uploadedAt;

    @ColumnDefault("'pending'")
    @Column(name = "verification_status", length = 20)
    private String verificationStatus;

    @Column(name = "verification_notes", length = Integer.MAX_VALUE)
    private String verificationNotes;

}