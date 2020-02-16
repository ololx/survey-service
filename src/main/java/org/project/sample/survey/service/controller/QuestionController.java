package org.project.sample.survey.service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.payload.ExceptionDetail;
import org.project.sample.survey.service.model.payload.QuestionDetail;
import org.project.sample.survey.service.service.QuestionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(
        value="QuestionController",
        description="Контроллер предоставляет CRUD-операции над сущностями \"Вопрос опроса\""
)
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
@Validated
@CrossOrigin(origins = "/**")
@RequestMapping(value = "survey/question")
@RestController
public class QuestionController {

    /** Сервис сущностей "Вопрос опроса" */
    QuestionService service;

    @ApiOperation(
            value = "Добавить сущность",
            notes = "Метод принимает запрос на добавление сущности"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Операция выполнена успешно <br />" +
                            "пример вывода <br />" +
                            "{\n" +
                            "  \"uid\": 4,\n" +
                            "  \"survey_uid\": 6,\n" +
                            "  \"number\": 1,\n" +
                            "  \"text\": \"Сильная ли у Вас текучка кадров\"\n" +
                            "}",
                    response = QuestionDetail.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Операция не выполнена - проверьте корректность данных",
                    response = ExceptionDetail.class
            )
    })
    @PostMapping(
            path = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    @JsonView(QuestionDetail.Create.class)
    public QuestionDetail create(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Вопрос опроса\" <br/>" +
                            "<b> пример запроса </b><br />" +
                            "{\n" +
                            "    \"survey_uid\": 6,\n" +
                            "    \"number\": 1,\n" +
                            "    \"text\": \"Сильная ли у Вас текучка кадров\"\n" +
                            "}",
                    required = true
            ) @Validated(
                    QuestionDetail.Create.class
            ) @RequestBody QuestionDetail request) {

        log.info(String.format("Получен запрос на добавление сущности \n Запрос - %s", request));

        return this.service.create(request);
    }

    @ApiOperation(
            value = "Обновить сущность",
            notes = "Метод принимает запрос на обновление сущности"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Операция выполнена успешно <br />" +
                            "пример вывода <br />" +
                            "{\n" +
                            "  \"uid\": 4,\n" +
                            "  \"survey_uid\": 6,\n" +
                            "  \"number\": 1,\n" +
                            "  \"text\": \"Сильная ли у Вас текучка кадров\"\n" +
                            "}",
                    response = QuestionDetail.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Операция не выполнена - проверьте корректность данных",
                    response = ExceptionDetail.class
            )
    })
    @PatchMapping(
            path = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    @JsonView(QuestionDetail.Update.class)
    public QuestionDetail update(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Вопрос опроса\" <br/>" +
                            "<b> пример запроса </b><br />" +
                            "{\n" +
                            "  \"uid\": 4,\n" +
                            "  \"survey_uid\": 6,\n" +
                            "  \"number\": 1,\n" +
                            "  \"text\": \"Сильная ли у Вас текучка кадров\"\n" +
                            "}",
                    required = true
            ) @Validated(
                    QuestionDetail.Update.class
            ) @RequestBody QuestionDetail request) {

        log.info(String.format("Получен запрос на обновление сущности \n Запрос - %s", request));

        return this.service.update(request);
    }

    @ApiOperation(
            value = "Удалить сущность",
            notes = "Метод принимает запрос на удаление сущности"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Операция выполнена успешно <br />" +
                            "пример вывода <br />" +
                            "{}",
                    response = QuestionDetail.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Операция не выполнена - проверьте корректность данных",
                    response = ExceptionDetail.class
            )
    })
    @DeleteMapping(
            path = "/delete",
            consumes = "application/json",
            produces = "application/json"
    )
    @JsonView(QuestionDetail.Delete.class)
    public QuestionDetail delete(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Вопрос опроса\" <br/>" +
                            "<b> пример запроса </b><br />" +
                            "{\n" +
                            "  \"uid\": 4,\n" +
                            "}",
                    required = true
            ) @Validated(
                    QuestionDetail.Delete.class
            ) @RequestBody QuestionDetail request) {

        log.info(String.format("Получен запрос на удаление сущности \n Запрос - %s", request));

        return this.service.delete(request);
    }

    @ApiOperation(
            value = "Получить сущность",
            notes = "Метод принимает запрос на выборку сущностей"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Операция выполнена успешно <br />" +
                            "пример вывода <br />" +
                            "[\n" +
                            "  {\n" +
                            "    \"uid\": 1,\n" +
                            "    \"survey_uid\": 1,\n" +
                            "    \"number\": 1,\n" +
                            "    \"text\": \"Сильная ли у Вас текучка кадров\"\n" +
                            "  },\n" +
                            "  {\n" +
                            "    \"uid\": 3,\n" +
                            "    \"survey_uid\": 1,\n" +
                            "    \"number\": 2,\n" +
                            "    \"text\": \"Есть ли у Вас оформление ДМС?\"\n" +
                            "  },\n" +
                            "  {\n" +
                            "    \"uid\": 4,\n" +
                            "    \"survey_uid\": 6,\n" +
                            "    \"number\": 1,\n" +
                            "    \"text\": \"Сильная ли у Вас текучка кадров\"\n" +
                            "  }\n" +
                            "]",
                    response = QuestionDetail.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Операция не выполнена - проверьте корректность данных",
                    response = ExceptionDetail.class
            )
    })
    @PostMapping(
            path = "/find",
            consumes = "application/json",
            produces = "application/json"
    )
    @JsonView(QuestionDetail.Find.class)
    public List<QuestionDetail> find(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Вопрос опроса\" <br/>" +
                            "примеры запросов (можно комбинировать, поля в запросе комбинируются дизъюнкцией) <br />" +
                            "<b> 1 -  Для вывода всех сущностей </b><br />" +
                            "{} <br />" +
                            "<b> 2 -  Для вывода первых двух сущностей с пагинацией и сортировкой </b><br />" +
                            "{\"search_filter\": {\n" +
                            "    \"fields\": \"uid\",\n" +
                            "    \"number\": 0,\n" +
                            "    \"direction\": \"ASC\",\n" +
                            "    \"size\": 2\n" +
                            "    }\n" +
                            "} <br />" +
                            "<b> 3 -  Для вывода сущностей с по идентификатору опроса </b><br />" +
                            "{\n" +
                            "    \"survey_uid\": 1" +
                            "} <br />" +
                            "<b> 4 -  Для вывода первых двух сущностей по идентификатору опроса с пагинацией " +
                            "и сортировкой </b><br />" +
                            "{\n" +
                            "    \"survey_uid\": \"1\",\n" +
                            "    \"search_filter\": {\n" +
                            "        \"fields\": \"uid\",\n" +
                            "        \"number\": 0,\n" +
                            "        \"direction\": \"ASC\",\n" +
                            "        \"size\": 2\n" +
                            "    }\n" +
                            "}",
                    required = true
            ) @Validated(
                    QuestionDetail.Find.class
            ) @RequestBody QuestionDetail request) {

        log.info(String.format("Получен запрос на выборку сущности \n Запрос - %s", request));

        return this.service.find(request);
    }
}
