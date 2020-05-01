package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "GovCheckGovResponseEntity")
@DiscriminatorValue("CHECK_REPORT")
public class GovCheckGovResponseEntity extends GovResponseEntity {

    @Column(name = "GOV_RES_ACC_ASSIGNED")
    private String accountAssigned;

}

