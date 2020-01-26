package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.reportSync.entity.ReportSyncRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportSyncRequestEntityRepository extends JpaRepository<ReportSyncRequestEntity, Long> {

}
