package com.whiteslave.whiteslaveApp.archiveReport.entity;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "REPORT_SYNC_REQUEST")
public class ReportSyncRequestEntity implements Serializable {

    @Id
    @Column(name = "RSY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RSY_REQUEST_DATE")
    private LocalDateTime requestDate;
    @Column(name = "RSY_REPORT_DATE")
    private LocalDate reportDate;
    @Column(name = "RSY_PDF_FILE_NAME")
    private String pdfFileName;
    @Enumerated(EnumType.STRING)
    @Column(name = "RSY_SEARCH_RESULT")
    private SearchResult searchResult;
    @Enumerated(EnumType.STRING)
    @Column(name = "RSY_REPORT_TYPE")
    private ReportType reportType;
    @Column(name = "RSY_REQUEST_NIP")
    private String requestNip;
    @Column(name = "RSY_REQUEST_REGON")
    private String requestRegon;
    @Column(name = "RSY_REQUEST_BANKACCOUNT")
    private String requestBankAccount;

    @OneToOne(cascade = CascadeType.ALL)
//    @MapsId
    @JoinColumn(name = "RSY_RESP_ID")
    private GovResponseEntity govResponseEntity;

    private void setId(Long id) {
        this.id = id;
    }
}
