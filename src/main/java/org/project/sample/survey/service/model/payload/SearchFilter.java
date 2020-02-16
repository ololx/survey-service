package org.project.sample.survey.service.model.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.project.sample.survey.service.model.SurveyServiceModel;
import org.project.sample.survey.service.service.filter.FilterFactory;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@ApiModel(
        value = "SearchFilter",
        description = "Модель фильтра для выборки сущности"
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
public final class SearchFilter implements FilterFactory.SearchRequest, SurveyServiceModel {

    @JsonIgnore
    String DEFAULT_SORTING_DIRECTION = "DESC";

    @JsonIgnore
    Integer DEFAULT_PAGE_NUMBER = 0;

    @ApiModelProperty(
            position = 1,
            notes = "Поля для сортировки <br />" +
                    "Указывать списком через разделитель -', ', если полей несколько <br />" +
                    "в конце разделитель - ', ' - не указывать"
                    + "ограничения для запросов: <br />"
                    + "#find - можно указать для выборки <br />",
            example = "startDate"
    )
    @JsonProperty("fields")
    String sortingFields;

    @ApiModelProperty(
            position = 2,
            notes = "Порядок сортировки <br />"
                    + "ограничения для запросов: <br />"
                    + "#find - можно указать для выборки <br />",
            example = "startDate"
    )
    @Pattern(
            regexp = "^(ASC|DESC)$",
            groups = Update.class,
            message = "Порядок сортировки может быть представлен либо \"ASC\", либо \"DESC\""
    )
    @JsonProperty("direction")
    String sortingDirection;

    @ApiModelProperty(
            position = 3,
            notes = "Номер страницы (текущая страница, начинается с 0) <br />"
                    + "ограничения для запросов: <br />"
                    + "#find - можно указать для выборки <br />",
            example = "1"
    )
    @PositiveOrZero(
            groups = {
                    Find.class
            },
            message = "Номер страницы должен быть больше 0"
    )
    @JsonProperty("number")
    Integer paginationLow;

    @ApiModelProperty(
            position = 4,
            notes = "Количество элементов на странице (верхний предел выборки, предел - 1) <br />"
                    + "ограничения для запросов: <br />"
                    + "#find - можно указать для выборки <br />",
            example = "1"
    )
    @PositiveOrZero(
            groups = {
                    Find.class
            },
            message = "Количество элементов на странице долно быть больше 0"
    )
    @JsonProperty("size")
    Integer paginationHight;

    @Override
    public boolean isSortable() {
        return this.sortingFields != null;
    }

    @Override
    public boolean isPageable() {
        return this.paginationHight != null;
    }

    public String getSortingDirection() {
        return this.sortingDirection == null
                ? this.DEFAULT_SORTING_DIRECTION
                : this.sortingDirection;
    }

    public Integer getPaginationLow() {
        return this.paginationLow == null
                ? this.DEFAULT_PAGE_NUMBER
                : this.paginationLow;
    }
}


