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
import org.project.sample.survey.service.service.validation.constraint.FinishDateMustBeGratherThan;

import javax.validation.constraints.*;
import java.util.Date;

@ApiModel(
        value = "SurveyDetail",
        description = "Модель сущности \"Опрос\""
)
@EntityMustExist(
        groups = {
                SurveyServiceModel.Update.class,
                SurveyServiceModel.Delete.class
        },
        message = "Такого опроса не существует в системе"
)
@FinishDateMustBeGratherThan(
        groups = {
                SurveyServiceModel.Create.class,
                SurveyServiceModel.Update.class
        }
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
public final class SurveyDetail implements SurveyServiceDetail {

    @ApiModelProperty(
            position = 1,
            notes = "Идентификатор опроса <br />"
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
            notes = "Наименование опроса <br />"
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
            example = "Опрос по интервью кандидата на вакантное место"
    )
    @Size(
            groups = {
                    Update.class,
                    Create.class
            },
            min = 1,
            max = 255,
            message = "Длина наименования опроса должна состоять из более 1 символа и не более 700 символов"
    )
    @NotBlank(
            groups = {
                    Create.class
            },
            message = "Наименование опроса быть задан и не может состоять из пробельных символов"
    )
    @NotEmpty(
            groups = {
                    Update.class
            },
            message = "Наименование опроса не может состоять из пробельных символов"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("name")
    String name;


    @ApiModelProperty(
            position = 3,
            notes = "Дата начала <br />"
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
            example = "2019-11-04"
    )
    @NotNull(
            groups = {
                    Create.class
            },
            message = "Дата начала должна быть задана"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("start_date")
    Date startDate;

    @ApiModelProperty(
            position = 4,
            notes = "Дата окончания <br />"
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
            example = "2019-11-08"
    )
    @NotNull(
            groups = {
                    Create.class
            },
            message = "Дата окончания должна быть задана"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("finish_date")
    Date finishDate;

    @ApiModelProperty(
            position = 5,
            notes = "Акутальность опроса (1 - активный, 2 - не активный) <br />"
                    + "ограничения для запросов: <br />"
                    + "#create - no matter <br />"
                    + "#update - no matter <br />"
                    + "#delete - no matter <br />"
                    + "#find - можно указать для выборки <br />"
                    + "видимость при выводе: <br />"
                    + "#create - возвращается <br />"
                    + "#update - возвращается <br />"
                    + "#find - возвращается <br />"
                    + "#delete - не возвращается <br />",
            example = "true"
    )
    @JsonView({
            Create.class,
            Update.class,
            Find.class
    })
    @JsonProperty("relevance")
    Boolean relevance;

    @ApiModelProperty(
            position = 6,
            notes = "Фильтр запроса <br />"
                    + "#find - можно указать для выборки",
            example = "true"
    )
    @JsonView({
            None.class
    })
    @JsonRawValue
    @JsonProperty("search_filter")
    SearchFilter searchFilter;

    public SearchFilter getSearchFilter() {
        return this.searchFilter == null
                ? new SearchFilter()
                : this.searchFilter;
    }
}
