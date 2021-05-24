package com.se.pickple_api_server.v1.report.infra.repository;

import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.report.domain.entity.QReport;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportQueryRepositoryImpl extends QuerydslRepositorySupport implements ReportQueryRepository {
    public ReportQueryRepositoryImpl() { super(Report.class);}

    @Override
    public Page<Report> search(SearchDto.Report searchRequest) {

        QReport report = QReport.report;
        JPQLQuery query = from(report);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    report.text.contains(searchRequest.getKeyword())
                .or(report.reporter.idString.contains((searchRequest.getKeyword()))));
        }
        if (searchRequest.getReportState() != null) {
            query.where(report.reportState.eq(searchRequest.getReportState()));
        }
        if (searchRequest.getReportResult() != null) {
            query.where(report.reportResult.eq(searchRequest.getReportResult()));
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Report> reportList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(reportList, pageable, totalElement);
    }
}
