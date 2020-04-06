package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchReportSubjectQueryRepository extends JpaRepository<SubjectEntity, Long> {

    List<SearchReportSubjectQueryDto> findBy();
}
