package com.calebematos.askfood.infrastructure.repository;

import com.calebematos.askfood.domain.model.ProductPhoto;
import com.calebematos.askfood.domain.repository.ProductRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;


	@Transactional
	@Override
	public ProductPhoto save(ProductPhoto productPhoto) {
		return manager.merge(productPhoto);
	}

	@Override
	public void delete(ProductPhoto productPhoto) {
		manager.remove(productPhoto);
	}
}
