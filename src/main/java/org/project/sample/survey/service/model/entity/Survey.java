package org.project.sample.survey.service.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

/** Сущность "Опрос" */
@Builder
@EqualsAndHashCode(exclude = "uid")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@Entity(name = "Survey")
@Table(name = "survey")
public final class Survey implements SurveyServiceEntity {

    /** Идентификатор опроса */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid",
            insertable = false,
            nullable = false)
    Long uid;

    /** Наименование опроса) */
    @Column(name = "name",
            nullable = false)
    String name;

    /** Дата начала) */
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date",
            nullable = false)
    Date startDate;

    /** Дата окончания */
    @Temporal(TemporalType.DATE)
    @Column(name = "finish_date",
            nullable = false)
    Date finishDate;

    /** Актуальност опроса (1 - активный, 0 - не активный) */
    @Column(name = "relevance",
            nullable = false)
    boolean relevance;

    {
        relevance = Boolean.TRUE;
    }
}