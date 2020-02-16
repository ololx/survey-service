package org.project.sample.survey.service.model;

import java.io.Serializable;

public interface SurveyServiceModel extends Serializable {

    interface Create {}

    interface Update {}

    interface Delete {}

    interface Find {}

    interface CRU extends CU, Find {}

    interface CRUD extends Create, Update, Delete, Find {}

    interface CUD extends Create, Update, Delete {}

    interface UD extends Update, Delete {}

    interface CU extends Create, Update {}

    interface None {}
}
