package org.project.sample.survey.service.service.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.entity.SurveyServiceEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@Service
public class CustomSpecificationBuilder<ENTITY extends SurveyServiceEntity> {

    /** Условие для выборки сущности */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class CustomCriteria {

        /** Операнд (поле сущности) */
        private String key;

        /** Опрерация */
        private String operation;

        /** Значение */
        private Object value;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class CustomSpecification<ENTITY> implements Specification<ENTITY> {

        private CustomCriteria criteria;

        @Override
        public Predicate toPredicate
                (Root<ENTITY> detail, CriteriaQuery<?> query, CriteriaBuilder builder) {

            if (criteria.getOperation().equalsIgnoreCase(">")) {

                return builder.greaterThanOrEqualTo(
                        detail.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase("<")) {

                return builder.lessThanOrEqualTo(
                        detail.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(":")) {

                if (detail.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(
                            detail.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(detail.get(criteria.getKey()), criteria.getValue());
                }
            }

            return null;
        }
    }

    private final List<CustomSpecification> catalogSpecifications;

    {
        catalogSpecifications = new ArrayList<>();
    }

    public CustomSpecificationBuilder with(String key, String operation, Object value) {
        catalogSpecifications.add(new CustomSpecification(new CustomCriteria(key, operation, value)));

        return this;
    }

    public CustomSpecificationBuilder withNotNull(String key, String operation, Object value) {
        if(value != null) {
            return this.with(key, operation, value);
        }

        return this;
    }

    public CustomSpecificationBuilder withEquals(String key, Object value) {
        return this.withNotNull(key, ":", value);
    }

    public CustomSpecificationBuilder withMore(String key, Object value) {
        return this.withNotNull(key, ">", value);
    }

    public CustomSpecificationBuilder withLess(String key, Object value) {
        return this.withNotNull(key, "<", value);
    }

    public Specification<ENTITY> build() {
        log.info("Спецификации \n {}", catalogSpecifications);

        if (catalogSpecifications.size() == 0) {
            return null;
        }

        Specification specification = catalogSpecifications.get(0);
        for (int i = 1; i < catalogSpecifications.size(); i++) {
            specification = Specification.where(specification)
                    .and(catalogSpecifications.get(i));
        }

        catalogSpecifications.clear();

        return specification;
    }
}
