package com.calebematos.askfood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurant> find(String name, BigDecimal initialFhippingFee, BigDecimal finalShippingFee) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);

		var predicates = new ArrayList<Predicate>();

		if (StringUtils.hasText(name)) {
			predicates.add(builder.like(root.get("name"), "%" + name + "%"));
		}

		if (initialFhippingFee != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("shippingFee"), initialFhippingFee));
		}

		if (finalShippingFee != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("shippingFee"), finalShippingFee));
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Restaurant> query = manager.createQuery(criteria);

		return query.getResultList();
	}

}
