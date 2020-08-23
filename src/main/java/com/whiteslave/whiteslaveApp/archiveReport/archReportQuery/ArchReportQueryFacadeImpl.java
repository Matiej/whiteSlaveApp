package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.repository.ReportSyncRequestEntityRepository;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.repository.SubjectEntityQueryRepository;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.CheckReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchNegativeReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchPositiveReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchReportDetailsQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.entity.ReportSyncRequestEntity;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
class ArchReportQueryFacadeImpl implements ArchReportQueryFacade {

    private final SubjectEntityQueryRepository subjectQueryRepository;
    private final ReportSyncRequestEntityRepository reportSyncRequestEntityRepository;

    @Override
    public List<CheckReportQueryView> allCheckReports() {
        return reportSyncRequestEntityRepository.findByReportType(ReportType.CHECK);
    }

    @Override
    public CheckReportQueryView findCheckReportTypeAndById(Long id) {
        return reportSyncRequestEntityRepository.findByReportTypeAndId(ReportType.CHECK, id);
    }

    /*
Lista pozytywnych search reports pobrana z Projekcji na klasę za pomocą JPA OPEN PROJECTION.
Poniedzielenie metod w celu przyspieszenia pobrania od strony bazy/
Zalozenie ze zdecydowanie wiecej pozytywnych bedzie jak negatywnych wyszukan.

Lista negatywnych search erpots pobrana po prostu z repozytorium archReportQueryRepository i
przekonwertowana.
 */
    @Override
    public List<SearchPositiveReportQueryView> allSearchReports() {
        List<SearchPositiveReportQueryView> searchPositiveReportQueryViewList = subjectQueryRepository.findAllBy();
        List<SearchNegativeReportQueryView> searchNegativeReportQueryViewList = reportSyncRequestEntityRepository
                .findAllByReportTypeAndSearchResult(ReportType.SEARCH, SearchResult.NEGATIVE);
        searchPositiveReportQueryViewList.addAll(searchNegativeReportQueryViewList);
        searchPositiveReportQueryViewList.sort(Comparator.comparing(SearchPositiveReportQueryView::getRequestDate).reversed());
        return searchPositiveReportQueryViewList;
    }

    @Override
    public SearchReportDetailsQueryView findSearchReportDetailsById(Long id) {
        return subjectQueryRepository.findOneById(id);
    }

    @Override
    public Resource findReportFileByEntityId(Long id) {
        ReportSyncRequestEntity requestEntity = reportSyncRequestEntityRepository.getOne(id);
        try {
            String pdfFileName = requestEntity.getPdfFileName();
            Path path = Paths.get(pdfFileName).normalize();
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {//todo przerobic aby tylko nazwa pliku byla w bazie danych. Sciezka na sztywno w konfiguracji aby nie bylo w logach widac dokladnie gdzie raporty
                throw new FileNotFoundException("File not found " + pdfFileName);
            }
        } catch (MalformedURLException  | RuntimeException e) {
            throw new FileNotFoundException("File report not found for id: " + id, e);
        }
    }
}
