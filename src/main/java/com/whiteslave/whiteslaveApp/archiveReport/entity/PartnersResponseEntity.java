package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "PARTNERS_RESPONSE")
public class PartnersResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAR_ID")
    private Long id;
    @Column(name = "PAR_COMPANY_NAME")
    private String companyName;
    @Column(name = "PAR_FIRST_NAME")
    private String firstName;
    @Column(name = "PAR_LAST_NAME")
    private String lastName;
    @Column(name = "PAR_NIP")
    private String nip;
    @Column(name = "PAR_PESEL")
    private String pesel;

}
