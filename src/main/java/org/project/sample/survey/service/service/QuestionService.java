package org.project.sample.survey.service.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.entity.Question;
import org.project.sample.survey.service.model.payload.QuestionDetail;
import org.project.sample.survey.service.repository.QuestionRepository;
import org.project.sample.survey.service.service.filter.QuestionFilterFactory;
import org.project.sample.survey.service.service.mapper.QuestionMapper;
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
public class QuestionService {

    /** Маппер опросов */
    QuestionMapper questionMapper;

    /** Рупозиторий сущности "Вопрос опроса" */
    QuestionRepository questionRepository;

    /** Фабрика условий выборки */
    QuestionFilterFactory filterFactory;

    /**
     * Метод добавляет сущность "Вопрос опроса" в БД
     * @param request модель сущности "Вопрос опроса"
     * @return модель сущности "Вопрос опроса"
     */
    public QuestionDetail create(QuestionDetail request) {
        //Создаем сущность
        Question question = this.questionMapper.toEntity(request);
        log.info("Создана сущность - {}", question);

        //Сохраняем сущность в БД
        this.questionRepository.save(question);

        QuestionDetail response = this.questionMapper.toDetail(question);
        log.info("Возвращаем ответ \n {}", response);

        return response;
    }

    /**
     * Метод обновляет сущность "Вопрос опроса" в БД
     * @param request модель сущности "Вопрос опроса"
     * @return модель сущности "Вопрос опроса"
     */
    public QuestionDetail update(QuestionDetail request) {
        //Выбираем сущность
        Question question = this.questionRepository.findByUid(request.getUid());
        log.info("Выбрана сущность - {}", question);

        //Обновляем сущность
        BeanUtils.copyProperties(request, question, PropertyUtil.getNullPropertyNames(request));
        this.questionRepository.save(question);

        QuestionDetail response = this.questionMapper.toDetail(question);
        log.info("Возвращаем ответ \n {}", response);

        return response;
    }

    /**
     * Метод удаляет сущность "Вопрос опроса" из БД
     * @param request модель сущности "Вопрос опроса"
     * @return модель сущности "Вопрос опроса"
     */
    public QuestionDetail delete(QuestionDetail request) {
        //Выбираем сущность
        Question question = this.questionRepository.findByUid(request.getUid());
        log.info("Выбрана сущность - {}", question);

        //Удаляем сущность
        this.questionRepository.delete(question);

        QuestionDetail response = this.questionMapper.toDetail(question);
        log.info("Возвращаем ответ \n {}", response);

        return response;
    }

    /**
     * Метод выводит сущности "Вопрос опроса" из БД
     * @param request модель сущности "Вопрос опроса"
     * @return модели сущностей "Вопрос опроса"
     */
    public List<QuestionDetail> find(QuestionDetail request) {
        //Создаем параметры запроса
        Specification specification = filterFactory.getSpecification(request);
        log.info("Созданы спецификации на основе запроса \n Специфкации - {} \n Запрос - {}", specification, request);
        Pageable pageable = filterFactory.getPageable(request.getSearchFilter());
        log.info("Созданы параметры сортировки на основе запроса \n Специфкации - {} \n Запрос - {}", pageable, request);

        //Выбираем сущность
        Page<Question> questions = this.questionRepository.findAll(specification, pageable);
        log.info("Выбраны сущности - {}", questions.getContent().toString());

        List<QuestionDetail> response = questions.map(eachQuestion -> this.questionMapper.toDetail(eachQuestion))
                .getContent();

        log.info("Возвращаем ответ \n {}", response);

        return response;
    }
}
