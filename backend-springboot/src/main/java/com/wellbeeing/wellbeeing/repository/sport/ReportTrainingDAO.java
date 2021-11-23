package com.wellbeeing.wellbeeing.repository.sport;

import com.wellbeeing.wellbeeing.domain.sport.ReportTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("reportTrainingDAO")
public interface ReportTrainingDAO extends JpaRepository<ReportTraining, Long>, JpaSpecificationExecutor<ReportTraining> {
}
