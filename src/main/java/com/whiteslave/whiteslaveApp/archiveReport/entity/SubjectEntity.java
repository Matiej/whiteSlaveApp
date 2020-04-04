package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUBJECT_RESPONSE")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUB_ID")
    private Long id;
    @Column(name = "SUB_NAME")
    private String name;
    @Column(name = "SUB_NIP")
    private String nip;
    @Column(name = "SUB_STATUS_VAT")
    private String statusVat;
    @Column(name = "SUB_REGON")
    private String regon;
    @Column(name = "SUB_PESEL")
    private String pesel;
    @Column(name = "SUB_KRS")
    private String krs;
    @Column(name = "SUB_RESIDENCE_ADDRESS")
    private String residenceAddress;
    @Column(name = "SUB_WORKING_ADDRESS")
    private String workingAddress;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "REP_SUB_ID")
    private List<RepresentativesResponseEntity> representativesEntityList;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "ACL_SUB_ID")
    private List<AuthorizedClerksResponseEntity> authorizedClerksResponseEntityList;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "PAR_SUB_ID")
    private List<PartnersResponseEntity> partnersResponseEntityList;

    @Column(name = "SUB_REGISTRATION_DATE")
    private LocalDate registrationLegalDate;
    @Column(name = "SUB_REGISTRATION_BASIS")
    private String registrationDenialBasis;
    @Column(name = "SUB_REGISTRATION_DENIAL_DATE")
    private LocalDate registrationDenialDate;
    @Column(name = "SUB_RESTORATION_DENIAL_BASIS")
    private String restorationBasis;
    @Column(name = "SUB_RESTORATION_DATE")
    private LocalDate restorationDate;
    @Column(name = "SUB_REMOVAL_BASIS")
    private String removalBasis;
    @Column(name = "SUB_REMOVAL_DATE")
    private LocalDate removalDate;
    @Column(name = "SUB_VIRTUAL_ACCOUNT")
    private Boolean hasVirtualAccounts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SUB_RES_ID")
    private SearchResponseReportEntity searchResponseReportEntity;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "BK_SUB_ID")
    private List<BankAccountEntity> bankAccountEntityList;


}
