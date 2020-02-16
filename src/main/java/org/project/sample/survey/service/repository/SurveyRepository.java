package org.project.sample.survey.service.repository;

import org.project.sample.survey.service.model.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    Survey findByUid(@Param("uid") Long uid);

    boolean existsByUid(@Param("uid") Long uid);
}