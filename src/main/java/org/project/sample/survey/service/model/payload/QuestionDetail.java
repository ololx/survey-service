package org.project.sample.survey.service.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.project.sample.survey.service.model.SurveyServiceModel;
import org.project.sample.survey.service.service.validation.constraint.EntityMustExist;
import org.project.sample.survey.service.service.validation.constraint.LinkedEntityMustExist;
import org.project.sample.survey.service.service.validation.constraint.QuestionNumberUnique;
import org.project.sample.survey.service.service.validation.constraint.QuestionTextUnique;

import javax.validation.constraints.*;

@ApiModel(
        value = "QuestionDetail",
        description = "Модель сущности \"Вопрос опроса\""
)
@EntityMustExist(
        groups = {
                SurveyServiceModel.Update.class,
                SurveyServiceModel.Delete.class
        },
        message = "Такого вопроса не существует в системе"
)
@LinkedEntityMustExist(
        groups = {
                SurveyServiceModel.Update.class,
                SurveyServiceModel.Create.class
        },
        message = "Такого опроса не существует в системе"
)
@QuestionNumberUnique(
        groups = {
                SurveyServiceModel.Create.class,
                SurveyServiceModel.Update.class
        },
        message = "Номера вопросов повторяются"
)
@QuestionTextUnique(
        groups = {
                SurveyServiceModel.Create.class,
                SurveyServiceModel.Update.class
        },
        message = "Тексты вопросов повторяются"
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
public final class QuestionDetail implements SurveyServiceDetail {

    @ApiModelProperty(
            position = 1,
            notes = "Идентификатор вопроса <br />"
                    + "ограничения для запросов: <br />"
                    + "#create - null <br />"
                    + "#update - not null <br />"
                    + "#delete - not null <br />"
                    + "#find - можно указать для выборки <br />"
                    + "видимость при выводе: <br />"
                    + "#create - возвращается <br />"
                    + "#update - возвращается <br />"
                    + "#find - возвращается <br />"
                    + "#delete - не возвращается <br />",
            example = "1"
    )
    @Null(
            groups = {
                    Create.class
            },
            message = "Идентификатор не должен быть задан"
    )
    @Min(
            groups = {
                    Delete.class,
                    Update.class,
                    Find.class
            },
            value = 1,
            message = "Идентификатор должен быть больше 1"
    )
    @NotNull(
            groups = {
                    Delete.class,
                    Update.class
            },
            message = "Идентификатор должен быть задан"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("uid")
    Long uid;

    @ApiModelProperty(
            position = 2,
            notes = "Идентификатор опроса <br />"
                    + "ограничения для запросов: <br />"
                    + "#create - not null <br />"
                    + "#update - no matter <br />"
                    + "#delete - no matter <br />"
                    + "#find - можно указать для выборки <br />"
                    + "видимость при выводе: <br />"
                    + "#create - возвращается <br />"
                    + "#update - возвращается <br />"
                    + "#find - возвращается <br />"
                    + "#delete - не возвращается <br />",
            example = "1"
    )
    @Min(
            groups = {
                    Create.class,
                    Find.class,
                    Update.class
            },
            value = 1,
            message = "Идентификатор опроса должен быть больше 1"
    )
    @NotNull(
            groups = {
                    Create.class
            },
            message = "Идентификатор опроса должен быть задан"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("survey_uid")
    Long surveyUid;

    @ApiModelProperty(
            position = 3,
            notes = "Номер вопроса (порядок отображения) <br />"
                    + "ограничения для запросов: <br />"
                    + "#create - not null <br />"
                    + "#update - no matter <br />"
                    + "#delete - no matter <br />"
                    + "#find - можно указать для выборки <br />"
                    + "видимость при выводе: <br />"
                    + "#create - возвращается <br />"
                    + "#update - возвращается <br />"
                    + "#find - возвращается <br />"
                    + "#delete - не возвращается <br />",
            example = "1"
    )
    @Min(
            groups = {
                    Create.class,
                    Update.class,
                    Find.class
            },
            value = 1,
            message = "Номер вопроса должен быть больше 1"
    )
    @NotNull(
            groups = {
                    Create.class
            },
            message = "Номер вопроса должен быть задан"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("number")
    Short number;

    @ApiModelProperty(
            position = 4,
            notes = "Текст вопроса (содержание) <br />"
                    + "ограничения для запросов: <br />"
                    + "#create - not null <br />"
                    + "#update - no matter <br />"
                    + "#delete - no matter <br />"
                    + "#find - можно указать для выборки <br />"
                    + "видимость при выводе: <br />"
                    + "#create - возвращается <br />"
                    + "#update - возвращается <br />"
                    + "#find - возвращается <br />"
                    + "#delete - не возвращается <br />",
            example = "Сильная ли у Вас текучка кадров"
    )
    @Size(
            groups = {
                    Update.class,
                    Create.class
            },
            min = 1,
            max = 700,
            message = "Длина текста вопроса должна состоять из более 1 символа и не более 700 символов"
    )
    @NotBlank(
            groups = {
                    Create.class
            },
            message = "Текст вопроса должен быть задан и не может состоять из пробельных символов"
    )
    @NotEmpty(
            groups = {
                    Update.class
            },
            message = "Текст вопроса не может состоять из пробельных символов"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("text")
    String text;

    @JsonView({
            None.class
    })
    @JsonRawValue
    @JsonProperty("search_filter")
    SearchFilter searchFilter;

    public SearchFilter getSearchFilter() {
        return this.searchFilter == null
                ? SearchFilter.builder()
                .sortingFields("number")
                .build()
                : this.searchFilter;
    }
}
