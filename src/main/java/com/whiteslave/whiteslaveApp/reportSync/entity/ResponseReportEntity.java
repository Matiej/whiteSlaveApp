package com.whiteslave.whiteslaveApp.reportSync.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity(name = "ReportEntity")
@Table(name = "REPORT_RESPONSE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="RES_TYPE",
        discriminatorType = DiscriminatorType.STRING)
public class ResponseReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RES_ID")
    private Long id;

    @Setter
    @Column(name = "RES_GOV_REQUEST_ID")
    private String requestId;

    @OneToOne(mappedBy = "responseReportEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private ReportSyncRequestEntity reportSyncRequestEntity;

}
