package org.project.sample.survey.service.service.mapper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.project.sample.survey.service.model.entity.Survey;
import org.project.sample.survey.service.model.payload.SurveyDetail;
import org.springframework.stereotype.Service;

@Slf4j
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
@Service
public final class SurveyMapper extends AbstractCustomModelMapper<Survey, SurveyDetail> {

    public SurveyMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Survey toEntity(SurveyDetail detail) {
        return this.modelMapper.map(detail, Survey.class);
    }

    @Override
    public SurveyDetail toDetail(Survey entity) {
        return this.modelMapper.map(entity, SurveyDetail.class);
    }
}