package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "AUTHORIZED_CLERKS_RESPONSE")
public class AuthorizedClerksResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACL_ID")
    private Long id;
    @Column(name = "ACL_COMPANY_NAME")
    private String companyName;
    @Column(name = "ACL_FIRST_NAME")
    private String firstName;
    @Column(name = "ACL_LAST_NAME")
    private String lastName;
    @Column(name = "ACL_NIP")
    private String nip;
    @Column(name = "ACL_PESEL")
    private String pesel;

}
