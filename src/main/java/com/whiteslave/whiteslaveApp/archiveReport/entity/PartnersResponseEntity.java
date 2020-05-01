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
@Table(name = "PARTNERS_RESPONSE")
public class PartnersResponseEntity implements Serializable {

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
