package org.project.sample.survey.service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.payload.ExceptionDetail;
import org.project.sample.survey.service.model.payload.SurveyDetail;
import org.project.sample.survey.service.service.SurveyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(
        value="SurveyController",
        description="Контроллер предоставляет CRUD-операции над сущностями \"Опроса\""
)
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
@Validated
@CrossOrigin(origins = "/**")
@RequestMapping(value = "survey")
@RestController
public class SurveyController {

    /** Сервис сущностей "Опрос" */
    SurveyService service;

    @ApiOperation(
            value = "Добавить сущность",
            notes = "Метод принимает запрос на добавление сущности"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Операция выполнена успешно <br />" +
                            "пример вывода <br />" +
                            "",
                    response = SurveyDetail.class
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
    @JsonView(SurveyDetail.Create.class)
    public SurveyDetail create(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Опрос\" <br/>" +
                            "<b> пример запроса </b><br />" +
                            "{\n" +
                            "    \"name\": \"Опрос по интервью кандидата на вакантное место\",\n" +
                            "    \"start_date\": \"2019-11-04\",\n" +
                            "    \"finish_date\": \"2019-11-08\",\n" +
                            "    \"relevance\": true\n" +
                            "}",
                    required = true
            ) @Validated(
                    SurveyDetail.Create.class
            ) @RequestBody SurveyDetail request) {

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
                            "",
                    response = SurveyDetail.class
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
    @JsonView(SurveyDetail.Update.class)
    public SurveyDetail update(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Опрос\" <br/>" +
                            "<b> пример запроса </b><br />" +
                            "{\n" +
                            "    \"uid\": 1,\n" +
                            "    \"name\": \"Опрос по интервью кандидата на вакантное место\",\n" +
                            "    \"start_date\": \"2019-11-04\",\n" +
                            "    \"finish_date\": \"2019-11-08\",\n" +
                            "    \"relevance\": true\n" +
                            "}",
                    required = true
            ) @Validated(
                    SurveyDetail.Update.class
            ) @RequestBody SurveyDetail request) {

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
                            "",
                    response = SurveyDetail.class
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
    @JsonView(SurveyDetail.Delete.class)
    public SurveyDetail delete(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Опрос\" <br/>" +
                            "<b> пример запроса </b><br />" +
                            "{\n" +
                            "    \"uid\": 1\n" +
                            "}",
                    required = true
            ) @Validated(
                    SurveyDetail.Delete.class
            ) @RequestBody SurveyDetail request) {

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
                            "    \"name\": \"Опрос по интервью кандидата на вакантное место\",\n" +
                            "    \"start_date\": \"2019-11-04\",\n" +
                            "    \"finish_date\": \"2019-11-08\",\n" +
                            "    \"relevance\": true\n" +
                            "  },\n" +
                            "  {\n" +
                            "    \"uid\": 2,\n" +
                            "    \"name\": \"Опрос по интервью кандидата на вакантное место\",\n" +
                            "    \"start_date\": \"2019-11-04\",\n" +
                            "    \"finish_date\": \"2019-11-08\",\n" +
                            "    \"relevance\": true\n" +
                            "  }\n" +
                            "]",
                    response = SurveyDetail.class,
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
    @JsonView(SurveyDetail.Find.class)
    public List<SurveyDetail> find(
            @ApiParam(
                    name="request",
                    value = "Модель сущности \"Опрос\" <br/>" +
                            "примеры запросов (можно комбинировать, поля в запросе комбинируются дизъюнкцией) <br />" +
                            "<b> 1 -  Для вывода всех сущностей </b><br />" +
                            "{} <br />" +
                            "<b> 2 -  Для вывода первых пяти сущностей с пагинацией и сортировкой </b><br />" +
                            "{\"search_filter\": {\n" +
                            "    \"fields\": \"uid\",\n" +
                            "    \"number\": 0,\n" +
                            "    \"direction\": \"ASC\",\n" +
                            "    \"size\": 2\n" +
                            "    }\n" +
                            "} <br />" +
                            "<b> 3 -  Для вывода первых двух сущностей с пагинацией и сортировкой </b><br />" +
                            "{\n" +
                            "    \"start_date\": \"2019-11-04\"\n" +
                            "} <br />" +
                            "<b> 4 -  Для вывода первых двух сущностей с пагинацией и сортировкой </b><br />" +
                            "{\n" +
                            "    \"start_date\": \"2019-11-04\",\n" +
                            "    \"search_filter\": {\n" +
                            "        \"fields\": \"uid\",\n" +
                            "        \"number\": 0,\n" +
                            "        \"direction\": \"ASC\",\n" +
                            "        \"size\": 2\n" +
                            "    }\n" +
                            "}",
                    required = true
            ) @Validated(
                    SurveyDetail.Find.class
            ) @RequestBody SurveyDetail request) {

        log.info(String.format("Получен запрос на выборку сущности \n Запрос - %s", request));

        return this.service.find(request);
    }
}
