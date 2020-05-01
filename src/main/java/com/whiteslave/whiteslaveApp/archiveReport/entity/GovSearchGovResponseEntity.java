package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "GovSearchGovResponseEntity")
@DiscriminatorValue("SEARCH_REPORT")
public class GovSearchGovResponseEntity extends GovResponseEntity {

    @Column(name = "GOV_RES_NO_OF_SUBJECT")
    private Integer subjectNo;

    @OneToMany(
            mappedBy = "govSearchResponseEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SubjectEntity> subjectEntityList;

}
