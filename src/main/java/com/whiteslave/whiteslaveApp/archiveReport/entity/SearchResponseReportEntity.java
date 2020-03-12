package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "SearchResponseReportEntity")
@DiscriminatorValue("SEARCH_REPORT")
public class SearchResponseReportEntity extends ResponseReportEntity {

    @Column(name = "RES_SCH_NO_OF_SUBJECT")
    private Integer subjectNo;

    @OneToMany(
            mappedBy = "searchResponseReportEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SubjectEntity> subjectEntityList;

}
