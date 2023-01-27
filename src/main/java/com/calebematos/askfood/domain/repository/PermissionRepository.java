package com.calebematos.askfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calebematos.askfood.domain.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
