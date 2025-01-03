package com.hoo.aoo.aar.adapter.out.persistence.repository;

import com.hoo.aoo.aar.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByNickname(String nickname);
}
