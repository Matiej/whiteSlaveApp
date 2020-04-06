package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class ArchReportQueryFacadeImpl implements ArchReportQueryFacade {

    private final SearchReportSubjectQueryRepository subjectQueryRepository;
    private final ArchRepotQueryRepository archRepotQueryRepository;
    private final ReportSyncRequestEntity2SearchReportSubjectQueryDto converter;

    /*
    Lista pozytywnych search reports pobrana z Projekcji na klasę za pomocą JPA OPEN PROJECTION.
    Poniedzielenie metod w celu przyspieszenia pobrania od strony bazy/
    Zalozenie ze zdecydowanie wiecej pozytywnych bedzie jak negatywnych wyszukan.

    Lista negatywnych search erpots pobrana po prostu z repozytorium archReportQueryRepository i
    przekonwertowana.
     */
    @Override
    public List<SearchReportSubjectQueryDto> findBy() {
        List<SearchReportSubjectQueryDto> searchPositiveReportList = subjectQueryRepository.findBy();
        List<SearchReportSubjectQueryDto> searchNegativeReportList = converter.convert2Dto(archRepotQueryRepository.findAllBySearchResult(SearchResult.NEGATIVE));
        return Stream.concat(searchNegativeReportList.stream(), searchPositiveReportList.stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckReportQueryDto> findAllCheckReports() {
        return archRepotQueryRepository.findAllCheckReports();
    }

    @Override
    public CheckReportQueryDto findCheckReportById(Long id) {
        return archRepotQueryRepository.findCheckReportById(id);
    }


}
