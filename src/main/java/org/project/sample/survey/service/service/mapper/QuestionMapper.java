package org.project.sample.survey.service.service.mapper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.project.sample.survey.service.model.entity.Question;
import org.project.sample.survey.service.model.payload.QuestionDetail;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
@Service
public final class QuestionMapper extends AbstractCustomModelMapper<Question, QuestionDetail> {

    public QuestionMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Question toEntity(QuestionDetail detail) {
        return this.modelMapper.map(detail, Question.class);
    }

    @Override
    public QuestionDetail toDetail(Question entity) {
        return this.modelMapper.map(entity, QuestionDetail.class);
    }

    public List<QuestionDetail> toDetails(Collection<Question> entities) {
        return entities.stream()
                .map(eachEntity -> this.toDetail(eachEntity))
                .collect(Collectors.toList());
    }

    public List<Question> toEntities(Collection<QuestionDetail> details) {
        return details.stream()
                .map(eachDetail -> this.toEntity(eachDetail))
                .collect(Collectors.toList());
    }
}