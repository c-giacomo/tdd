package it.testmodel.specification;

import java.util.Date;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import it.testdate.model.Entita;

public class TestSpecification {
	
	public static Specification<Entita> findByDataGreaterOrEqual(Date data) {
		if (data == null)
			return (root, query, cb) -> cb.conjunction();
		else return (root, query, cb) -> {
			Path<Date> startDate = root.<Date>get("data1");
			Predicate startPredicate = cb.or(cb.isNull(startDate), cb.greaterThanOrEqualTo(startDate, data));
			Path<Date> endDate = root.<Date>get("data2");
			Predicate endPredicate = cb.or(cb.isNull(endDate), cb.lessThanOrEqualTo(endDate, data));
			return cb.and(startPredicate, endPredicate);
		};
	}
	
//	public static Specification<Entita> findByDataLessOrEqual(Date data) {
//		if (data == null)
//			return (root, query, cb) -> cb.conjunction();
//		else return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("data2"), data);
//	}

}
