package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.archiveReport.entity.ReportSyncRequestEntity;

import java.util.Optional;

class ReportSyncRequestRepositoryTest implements ReportSyncRequestRepository {

    @Override
    public <S extends ReportSyncRequestEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends ReportSyncRequestEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<ReportSyncRequestEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<ReportSyncRequestEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<ReportSyncRequestEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ReportSyncRequestEntity reportSyncRequestEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends ReportSyncRequestEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
