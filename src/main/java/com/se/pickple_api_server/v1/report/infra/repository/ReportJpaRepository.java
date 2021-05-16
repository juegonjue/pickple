package com.se.pickple_api_server.v1.report.infra.repository;

import com.se.pickple_api_server.v1.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportJpaRepository extends JpaRepository<Report, Long> {
}
