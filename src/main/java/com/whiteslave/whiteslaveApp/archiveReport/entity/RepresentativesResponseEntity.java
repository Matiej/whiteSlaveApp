package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REPRESENTATIVES_RESPONSE")
public class RepresentativesResponseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REP_ID")
    private Long id;
    @Column(name = "REP_COMPANY_NAME")
    private String companyName;
    @Column(name = "REP_FIRST_NAME")
    private String firstName;
    @Column(name = "REP_LAST_NAME")
    private String lastName;
    @Column(name = "REP_NIP")
    private String nip;
    @Column(name = "REP_PESEL")
    private String pesel;

}
