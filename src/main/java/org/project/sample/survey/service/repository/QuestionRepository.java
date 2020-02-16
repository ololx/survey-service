package org.project.sample.survey.service.repository;

import org.project.sample.survey.service.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    Question findByUid(@Param("uid") Long uid);

    boolean existsByUid(@Param("uid") Long uid);

    boolean existsBySurveyUid(@Param("surveyUid") Long surveyUid);

    boolean existsBySurveyUidAndNumberAndUidNot(
            @Param("surveyUid") Long surveyUid,
            @Param("number") Short number,
            @Param("uid") Long uid
    );

    boolean existsBySurveyUidAndNumber(
            @Param("surveyUid") Long surveyUid,
            @Param("number") Short number
    );

    boolean existsByTextIgnoreCase(
            @Param("text") String text
    );
}