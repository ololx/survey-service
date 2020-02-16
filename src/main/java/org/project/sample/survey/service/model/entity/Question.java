package org.project.sample.survey.service.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/** Сущность "Вопрос опроса" */
@Builder
@EqualsAndHashCode(exclude = "uid")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@Entity(name = "Question")
@Table(name = "question")
public final class Question implements SurveyServiceEntity{

    /** Идентификатор вопроса */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid",
            insertable = false,
            nullable = false)
    Long uid;

    /** Идентификатор опроса */
    @Column(name = "survey_uid",
            nullable = false)
    Long surveyUid;

    /** Номер вопроса (порядок отображения) */
    @Column(name = "number",
            nullable = false)
    Short number;

    /** Текст вопроса (содержание) */
    @Column(name = "text",
            nullable = false)
    String text;
}