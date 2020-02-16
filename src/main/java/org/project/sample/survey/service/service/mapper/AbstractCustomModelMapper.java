package org.project.sample.survey.service.service.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.project.sample.survey.service.model.SurveyServiceModel;
import org.project.sample.survey.service.model.entity.SurveyServiceEntity;
import org.project.sample.survey.service.model.payload.SurveyServiceDetail;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(
        level = AccessLevel.PROTECTED,
        makeFinal = true
)
@Service
public abstract class AbstractCustomModelMapper<ENTITI extends SurveyServiceEntity, DETAIL extends SurveyServiceDetail>
        implements CustomModelMapper<ENTITI, DETAIL> {

    /** Маппер модели */
    ModelMapper modelMapper;

    public <MODEL extends SurveyServiceModel> MODEL getModel(SurveyServiceModel model, Class<MODEL> typeParameterClass) {
        return modelMapper.map(model, typeParameterClass);
    }

    public abstract ENTITI toEntity(DETAIL detail);

    public abstract DETAIL toDetail(ENTITI entity);
}