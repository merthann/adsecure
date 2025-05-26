package com.adsecure.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adsecure.webapp.repositories.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByMail(String mail);
    boolean existsByMail(String mail);
}
