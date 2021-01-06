package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponse;
import com.whiteslave.whiteslaveApp.reportSync.domain.GovResponse;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

class ArchReportServiceTestBase {

    static ReportSyncRequestBuilder builder() {
        return new ReportSyncRequestBuilder();
    }

    static class ReportSyncRequestBuilder {

        static GovCheckResponseBuilder checkBuilder() {
            return new GovCheckResponseBuilder();
        }

        private LocalDateTime requestDate = LocalDateTime.of(2020, 11, 28, 12, 35, 44);
        private LocalDate reportDate = LocalDate.of(2020, 10, 22);
        private SearchResult searchResult = SearchResult.POSITIVE;
        private ReportType reportType = ReportType.CHECK;
        private GovResponse govResponse = new GovResponse("requestID123");
        private String pdfFileName = "";
        private String requestNip = "1281481416";
        private String requestRegon = "0147985";
        private String requestBankAccount = "PL38928810111291834777382226";

        ReportSyncRequestBuilder withRequestDate(int year, int month, int day,
                                                 int hour, int min, int sec) {
            this.requestDate = LocalDateTime.of(year, month, day, hour, min, sec);
            return this;
        }

        ReportSyncRequestBuilder withReportDate(int year, int month, int day) {
            this.reportDate = LocalDate.of(year, month, day);
            return this;
        }

        ReportSyncRequestBuilder withSearchResult(SearchResult searchResult) {
            this.searchResult = searchResult;
            return this;
        }

        ReportSyncRequestBuilder withReportType(ReportType reportType) {
            this.reportType = reportType;
            return this;
        }

        ReportSyncRequestBuilder withGovResponse(GovResponse govResponse) {
            this.govResponse = govResponse;
            return this;
        }

        ReportSyncRequestBuilder withPdfFileName(String pdfFileName) {
            this.pdfFileName = pdfFileName;
            return this;
        }

        ReportSyncRequestBuilder withRequestNip(String nip) {
            this.requestNip = nip;
            return this;
        }

        ReportSyncRequestBuilder withRequestRegon(String regon) {
            this.requestRegon = regon;
            return this;
        }

        ReportSyncRequestBuilder withRequestBankAccount(String bankAccount) {
            this.requestBankAccount = bankAccount;
            return this;
        }

        ReportSyncRequest build() {
            ReportSyncRequest reportSyncRequest = new ReportSyncRequest(requestDate, reportDate, searchResult, reportType,
                    govResponse, pdfFileName, requestNip, requestRegon, requestBankAccount);

            return reportSyncRequest;
        }

        static class GovCheckResponseBuilder {

            private String requestId = "requst_123123_ID";
            private String accountAssigned = "TAK";

            GovCheckResponseBuilder withRequestId(String requestId) {
                this.requestId = requestId;
                return this;
            }

            GovCheckResponseBuilder withAccountAssignedTakPositive() {
                this.accountAssigned = "TAK";
                return this;
            }


            GovCheckResponseBuilder withAccountAssignedNieNegative() {
                this.accountAssigned = "NIE";
                return this;
            }

            CheckGovResponse build() {
                return new CheckGovResponse(requestId, accountAssigned);
            }

        }

    }
}
