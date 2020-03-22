package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "CheckResponseReportEntity")
@DiscriminatorValue("CHECK_REPORT")
public class CheckResponseReportEntity extends ResponseReportEntity {

    @Column(name = "RES_CCH_ACC_ASSIGNED")
    private String accountAssigned;

}

