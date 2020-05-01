package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity(name = "ReportEntity")
@Table(name = "GOV_RESPONSE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="RES_TYPE",
        discriminatorType = DiscriminatorType.STRING)
public abstract class GovResponseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOV_RES_ID")
    private Long id;

    @Setter
    @Column(name = "GOV_RES_REQUEST_ID")
    private String requestId;

    @OneToOne(mappedBy = "govResponseEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private ReportSyncRequestEntity reportSyncRequestEntity;

}
