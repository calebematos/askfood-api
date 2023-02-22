package com.calebematos.askfood.domain.repository;

import com.calebematos.askfood.domain.model.User;

import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
}
