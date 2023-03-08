package com.calebematos.askfood.infrastructure.service;

import com.calebematos.askfood.domain.filter.DailySaleFilter;
import com.calebematos.askfood.domain.model.OrderStatus;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.model.dto.DailySale;
import com.calebematos.askfood.domain.service.SalesQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SalesQueryServiceImpl implements SalesQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySale> consultDailySales(DailySaleFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Ordering.class);

        var functionDateRegistrationDate = builder.function("date", Date.class, root.get("registrationDate"));

        var selection = builder.construct(DailySale.class,
                functionDateRegistrationDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue")));

        var predicates = new ArrayList<Predicate>();

        predicates.add(root.get("status").in(OrderStatus.CONFIRMED, OrderStatus.DELIVERED));

        if (filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }
        if (filter.getRegistrationDateStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("registrationDate"),filter.getRegistrationDateStart()));
        }
        if(filter.getRegistrationDateEnd() != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("registrationDate"),filter.getRegistrationDateEnd()));
        }

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateRegistrationDate);

        return manager.createQuery(query).getResultList();
    }
}
