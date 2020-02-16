package org.project.sample.survey.service.model.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@ApiModel(
        value = "ExceptionDetail",
        description = "Детализация ошибки"
)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
public class ExceptionDetail {

    @ApiModelProperty(notes = "Дата возникновения")
    Date timestamp;

    @ApiModelProperty(notes = "Комментарий к исключению")
    String comment;

    @ApiModelProperty(notes = "Сообщение исключения")
    String message;

    @ApiModelProperty(notes = "Детализация запроса")
    String details;
}
