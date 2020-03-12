package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.reportSync.entity.ReportSyncRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportSyncRequestRepository extends CrudRepository<ReportSyncRequestEntity, Long> {
}
