package org.project.sample.survey.service.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.entity.Survey;
import org.project.sample.survey.service.model.payload.SurveyDetail;
import org.project.sample.survey.service.repository.SurveyRepository;
import org.project.sample.survey.service.service.filter.SurveyFilterFactory;
import org.project.sample.survey.service.service.mapper.SurveyMapper;
import org.project.sample.survey.service.service.utils.PropertyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
@Service
public class SurveyService {

    /** Маппер опросов */
    SurveyMapper surveyMapper;

    /** Рупозиторий сущности "Опрос" */
    SurveyRepository surveyRepository;

    /** Фабрика условий выборки сущности "Опрос" */
    SurveyFilterFactory surveyFilterFactory;

    /**
     * Метод добавляет сущность "Опрос" в БД
     * @param request модель сущности "Опрос"
     * @return модель сущности "Опрос"
     */
    public SurveyDetail create(SurveyDetail request) {
        //Создаем сущность
        Survey survey = this.surveyMapper.toEntity(request);
        log.info("Создана сущность - {}", survey);

        //Сохраняем сущность в БД
        this.surveyRepository.save(survey);

        SurveyDetail response = this.surveyMapper.toDetail(survey);
        log.info("Возвращаем ответ \n {}", response);

        return response;
    }

    /**
     * Метод обновляет сущность "Опрос" в БД
     * @param request модель сущности "Опрос"
     * @return модель сущности "Опрос"
     */
    public SurveyDetail update(SurveyDetail request) {
        //Выбираем сущность
        Survey survey = this.surveyRepository.findByUid(request.getUid());
        log.info("Выбрана сущность - {}", survey);

        //Обновляем сущность
        BeanUtils.copyProperties(request, survey, PropertyUtil.getNullPropertyNames(request));
        this.surveyRepository.save(survey);

        SurveyDetail response = this.surveyMapper.toDetail(survey);
        log.info("Возвращаем ответ \n {}", response);

        return response;
    }

    /**
     * Метод удаляет сущность "Опрос" из БД
     * @param request модель сущности "Опрос"
     * @return модель сущности "Опрос"
     */
    public SurveyDetail delete(SurveyDetail request) {
        //Выбираем сущность
        Survey survey = this.surveyRepository.findByUid(request.getUid());
        log.info("Выбрана сущность - {}", survey);

        //Удаляем сущность
        this.surveyRepository.delete(survey);

        SurveyDetail response = this.surveyMapper.toDetail(survey);
        log.info("Возвращаем ответ \n {}", response);

        return response;
    }

    /**
     * Метод выводит сущности "Опрос" из БД
     * @param request модель сущности "Опрос"
     * @return модели сущностей "Опрос"
     */
    public List<SurveyDetail> find(SurveyDetail request) {
        //Создаем параметры запроса
        Specification specification = surveyFilterFactory.getSpecification(request);
        log.info("Созданы спецификации на основе запроса \n Специфкации - {} \n Запрос - {}", specification, request);
        Pageable pageable = surveyFilterFactory.getPageable(request.getSearchFilter());
        log.info("Созданы параметры сортировки на основе запроса \n Специфкации - {} \n Запрос - {}", pageable, request);

        //Выбираем сущность
        Page<Survey> surveys = this.surveyRepository.findAll(specification, pageable);
        log.info("Выбраны сущности - {}", surveys.getContent().toString());

        List<SurveyDetail> response = surveys.map(eachSurvey -> this.surveyMapper.toDetail(eachSurvey))
                .getContent();

        log.info("Возвращаем ответ \n {}", response);

        return response;
    }
}
