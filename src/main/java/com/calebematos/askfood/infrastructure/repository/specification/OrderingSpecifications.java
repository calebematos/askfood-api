package com.calebematos.askfood.infrastructure.repository.specification;

import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.filter.OrderingFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderingSpecifications {

	public static Specification<Ordering> usingFilter(OrderingFilter filter) {
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();

			if(Ordering.class.equals(query.getResultType())) {
				root.fetch("restaurant").fetch("cuisine");
				root.fetch("client");
			}

			if(filter.getClientId() != null){
				predicates.add(builder.equal(root.get("client"),filter.getClientId()));
			}
			if(filter.getRestaurantId() != null){
				predicates.add(builder.equal(root.get("restaurant"),filter.getRestaurantId()));
			}
			if(filter.getRegistrationDateStart() != null){
				predicates.add(builder.greaterThanOrEqualTo(root.get("registrationDate"),filter.getRegistrationDateStart()));
			}
			if(filter.getRegistrationDateEnd() != null){
				predicates.add(builder.lessThanOrEqualTo(root.get("registrationDate"),filter.getRegistrationDateEnd()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};

	}



}
