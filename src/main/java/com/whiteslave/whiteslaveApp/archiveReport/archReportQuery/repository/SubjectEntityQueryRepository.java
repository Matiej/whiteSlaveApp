package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.repository;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchPositiveReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchReportDetailsQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectEntityQueryRepository extends JpaRepository<SubjectEntity, Long> {
    List<SearchPositiveReportQueryView> findAllBy();

    SearchReportDetailsQueryView findOneById(Long id);
}