package com.whiteslave.whiteslaveApp.reportSync.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "CheckResponseReportEntity")
@DiscriminatorValue("CHECK_REPORT")
public class CheckResponseReportEntity extends ResponseReportEntity {

    @Column(name = "RES_CCH_ACC_ASSIGNED")
    private String accountAssigned;

}

