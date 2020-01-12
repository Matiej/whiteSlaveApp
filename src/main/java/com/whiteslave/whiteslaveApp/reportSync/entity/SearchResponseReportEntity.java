package com.whiteslave.whiteslaveApp.reportSync.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "SearchResponseReportEntity")
@DiscriminatorValue("SEARCH_RESPONSE_REPORT")
public class SearchResponseReportEntity extends ResponseReportEntity {

    @Column(name = "RES_SCH_NO_OF_SUBJECT")
    private Integer subjectNo;

}
