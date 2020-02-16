package org.project.sample.survey.service.service.mapper;

import org.project.sample.survey.service.model.entity.SurveyServiceEntity;
import org.project.sample.survey.service.model.payload.SurveyServiceDetail;

public interface CustomModelMapper<ENTITI extends SurveyServiceEntity, DETAIL extends SurveyServiceDetail> {

    ENTITI toEntity(DETAIL detail);

    DETAIL toDetail(ENTITI entity);
}
